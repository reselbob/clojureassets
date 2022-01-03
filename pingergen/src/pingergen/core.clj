(ns pingergen.core
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.data.json :as json]
            [clj-memory-meter.core :as mm])
  (:gen-class))

; Simple Body Page
(defn simple-body-page [req]                                ;(3)
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Hello World"})
;
; request-handler
(defn request-handler [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (->
             (pp/pprint req)
             (str "Request Object: " req))})

; Hello-name handler
(defn ping-handler [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (->
             (pp/pprint req)
             (str "Ping at " (.toString (java.util.Date.))))})

; my people-collection mutable collection vector
(def people-collection (atom []))

;Collection Helper functions to add a new person
(defn addperson [firstname surname]
  (swap! people-collection conj {:firstname (str/capitalize firstname)
                                 :surname (str/capitalize surname)}))

; Example JSON objects
(addperson "Functional" "Human")
(addperson "Micky" "Mouse")

; Return List of People
(defn memory-handler [req]
  {:status  200
   :headers {"Content-Type" "text/json"}
   :body    (str (json/write-str @people-collection))})

; Helper to get the parameter specified by pname from :params object in req
(defn getparameter [req pname] (get (:params req) pname))

; Add a new person into the people-collection
(defn envars-handler [req]
  {:status  200
   :headers {"Content-Type" "text/json"}
   :body    (-> (pp/pprint (System/getenv)) 
             (str (json/write-str (System/getenv))))})

; Our main routes
(defroutes app-routes
  (GET "/" [] simple-body-page)
  (GET "/request" [] request-handler)
  (GET "/ping" [] ping-handler)
  (GET "/memory" [] memory-handler)
  (GET "/envars" [] envars-handler)
  (route/not-found "Error, API endpoint not found!"))

; Our main entry function
(defn -main
  "This is our main entry point"
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
    ; Run the server with Ring.defaults middleware
    (server/run-server (wrap-defaults #'app-routes site-defaults) {:port port})
    ; Run the server without ring defaults
    ;(server/run-server #'app-routes {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))