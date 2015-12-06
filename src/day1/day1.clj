(ns day1.day1)

(def input
  (slurp "day1.in"))

(defn input-filtered [character]
  (filter #(= % character)
          (seq input)))

(def up
  (count (input-filtered \()))
(def down
  (count (input-filtered \))))

(def final-floor
  (- up down))

; part 1 solution
(println
  final-floor)

(defn move-direction [char]
  (if (= char \() 1 -1))

(defn find-enter-basement [input]
  (loop [remaining input
         index 0
         current-floor 0]
    (if (< current-floor 0)
      index
      (recur
        (rest remaining)
        (inc index)                                         ; know the index
        (+ current-floor (move-direction (first remaining))))))) ; recur by adding to the floor the up/down direction

(def basement-entry
  (find-enter-basement input))

; part 2 solution
(println basement-entry)