(ns misr.extract)
(require '[clojure.java.shell :as shell])
(import java.io.File)

(defmacro with-temp-dir [dir-name & body]
  `(let [~dir-name (str (gensym))]
     (.mkdir (File. ~dir-name))
     ~@body
     ;(shell/sh "rm" "-r" ~dir-name)
     ))

(defn drop-last-str [s i]
  (.substring s 1 (- (count s) i)))

(defn extract-column [temp-dir f col]
  (spit
    (str temp-dir "/" col)
    (->
      (shell/sh "ncdump" "-v" col f)
      :out
      (.split "=")
      last
      (drop-last-str 5)
      (.replace "\n" ",")
      (.replace "," "\n")
      )))

(defn extract-columns [source target & cols]
  (with-temp-dir f
    (dorun (pmap #(extract-column f source %) cols))
    (spit target
          (:out (apply shell/sh "paste" "-d," (map #(str f "/" %) cols))))
    ))

(extract-columns "../../Downloads/x.nc" "../../Downloads/test.txt" "Latitude" "CloudTopAltitude")
(def s (extract-column "../../Downloads/x.nc" "Latitude"))


(with-out-str
  (doc shell/sh))
