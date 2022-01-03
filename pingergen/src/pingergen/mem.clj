(ns pingergen.mem
  (:require [clojure.string])
  (:gen-class))

(def total (float (/  (.totalMemory (Runtime/getRuntime)) 1024)))
(def free (float (/ (.freeMemory (Runtime/getRuntime)) 1024)))
(def used (float (- total free)))

(defn get-mem [] {:total-mem total
                  :free-mem free
                  :used-mem used})

