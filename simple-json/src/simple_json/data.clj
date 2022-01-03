(ns simple-json.data
  (:require [clojure.data.json :as json])
  (:gen-class))

 (defn getdata [datapath] (clojure.data.json/read-str (slurp datapath)))