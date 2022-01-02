(ns simple-json.core
  (require '[clojure.core])
  (require '[clojure.string :as str])
  (require '[clojure.data.json :as json])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (def all-records (clojure.data.json/read-str (slurp "/root/data/big-data.json")))
  (println all-records))
