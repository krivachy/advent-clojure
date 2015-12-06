(ns day3.day3
  (:require [clojure.set :as set]))

(defrecord Position [x y])

(defn next-position [position direction]
  (case direction
    \< (Position. (+ (:x position) -1) (:y position))
    \> (Position. (+ (:x position) 1) (:y position))
    \^ (Position. (:x position) (+ (:y position) 1))
    \v (Position. (:x position) (+ (:y position) -1))))

(def initial-pos
  (Position. 0 0))
; Called a map, but in reality a set
(def initial-map
  ; first present is already delivered
  #{initial-pos})

(def input (slurp "day3.in"))

(defn travel-map [route]
  (loop [remaining-route route
         current-position initial-pos
         map initial-map]
    (if (empty? remaining-route)
      map
      (let [next-pos (next-position current-position (first remaining-route))]
        (recur
          (rest remaining-route)
          next-pos
          (conj map next-pos))))))

(def traveled-map (travel-map input))

(def at-least-one-present (count traveled-map))

; solution to part 1
(println at-least-one-present)


; PART 2
(def santa-route
  (take-nth 2 input))

(def robot-route
  (take-nth 2 (rest input)))

(def santas-map (travel-map santa-route))
(def robots-map (travel-map robot-route))

(def full-map (set/union santas-map robots-map))

(println (count full-map))