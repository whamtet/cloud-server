(ns misr.extract2)

(import ucar.nc2.NetcdfFile)
(def v
  (let [open (NetcdfFile/open "../../Downloads/x.nc")]
    (.findVariable open "OrbitNumber")))

(-> v .read .getStorage alength)
