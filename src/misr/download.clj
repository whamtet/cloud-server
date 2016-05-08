(ns misr.download)

(def sample-url "http://l0dup05.larc.nasa.gov/opendap/misrl2l3/MISR/MI3MCMVN.002/%04d.%02d.01/MISR_AM1_CMV_%s_%d_F02_0002.nc")
;http://l0dup05.larc.nasa.gov/opendap/misrl2l3/MISR/MI3MCMVN.002/2001.01.01/MISR_AM1_CMV_JAN_2001_F02_0002.nc

(require '[clojure.java.shell :as sh])

(def s (:out (sh/sh "ls" :dir "../../Downloads")))

(def months (drop 3 (cycle (range 1 13))))

(def years (drop 3 (drop-last 8 (mapcat #(repeat 12 %) (range 2000 2017)))))

(def month-names ["JAN" "FEB" "MAR" "APR" "MAY" "JUN" "JUL" "AUG" "SEP" "OCT" "NOV" "DEC"])
(def month-names2 (drop 3 (cycle month-names)))

(def urls (map (fn [year month month-name] (format sample-url year month month-name year)) years months month-names2))

(defn download [url] (sh/sh "wget" url :dir "../../clouds"))

