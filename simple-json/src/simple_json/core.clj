(ns simple-json.core
  (:require [clojure.string :as str]
            [simple-json.data :as data])
  (:gen-class))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]

  (if (not (str/blank? (first *command-line-args*)))
    (print (data/getdata (first *command-line-args*)))
    (print "ERROR: No filepath provided to the require JSON data file.")
    )
  )