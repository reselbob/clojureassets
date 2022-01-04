(ns pingergen.core
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [clojure.pprint :as pp]
            [clojure.data.json :as json]
            [pingergen.mem :as mem])
  (:gen-class))

; Simple Body Page
(defn simple-body-page [req]                                ;(3)
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Welcome to Pingergen!"})
;
; request-handler
(defn request-handler [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (->
             (pp/pprint req)
             (str req))})

; Return a simple string
(defn ping-handler [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (->
             (pp/pprint req)
             (str "Pingergen called at " (.toString (java.util.Date.))))})


; Return map with memory data
(defn memory-handler [req]
  {:status  200
   :headers {"Content-Type" "text/json"}
   :body    (-> (pp/pprint (mem/get-mem))
                (str (json/write-str (mem/get-mem))))})

(defn getparameter [req pname] (get (:params req) pname))

; Get the env vars
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
    (println (str "Running Pingergen API webserver at http:/127.0.0.1:" port "/"))))