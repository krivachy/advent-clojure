(ns day3.day3)

(defrecord Position [x y])

(defn next-position [position direction]
  (case direction
    \< (Position. (+ (:x position) -1) (:y position))
    \> (Position. (+ (:x position) 1) (:y position))
    \^ (Position. (:x position) (+ (:y position) 1))
    \v (Position. (:x position) (+ (:y position) -1))))

(def initial-pos
  (Position. 0 0))
(def initial-map
  ; first present is already delivered
  {initial-pos 1})

(def input (slurp "day3.in"))

(defn move-on-map [map next-pos]
  (merge map
         {next-pos (+ (get map next-pos 0) 1)}))

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
          (move-on-map map next-pos))))))

(def traveled-map (travel-map input))

(def at-least-one-present (count traveled-map))

; solution to part 1
(println at-least-one-present)