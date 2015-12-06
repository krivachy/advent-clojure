(ns day5.day5)

(def vowels #{\a \e \i \o \u})
(defn vowel? [char]
  (contains? vowels char))

; It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
(defn rule-1 [s]
  (>= (count (filter vowel? s)) 3))
; It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa, bb, cc, or dd).
(defn rule-2 [s]
  (loop [string s]
    (if (< (count string) 2)
      false
      (if (= (first string) (first (rest string)))
        true
        (recur (rest string))))))

(def bad-strings #{"ab", "cd", "pq", "xy"})

; It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.
(defn rule-3 [s]
  (loop [string s]
    (if (< (count string) 2)
      true
      (if (contains? bad-strings (apply str (take 2 string)))
        false
        (recur (rest string))))))

(def input (clojure.string/split-lines (slurp "day5.in")))

(defn nice-string? [string]
  (and (rule-1 string) (rule-2 string) (rule-3 string)))

(defn count-nice-strings [input-rows]
  (count (filter nice-string? input-rows)))

(println (count-nice-strings input))