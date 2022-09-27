(ns triangulo.core
  (:require [clojure.math :as math]))

(defn calc-perimetro
  "Calcula o perimetro do triangulo, dado A B e C"
  [a b c]
  (+ a b c))

(defn angulo
  "Calcula o angulo"
  [a b c]
  (math/acos (/ (- (+ (math/pow b 2)
                      (math/pow c 2))
                   (math/pow a 2))
                (* 2 b c))))

(defn calc-radianos
  "TODO: Calcular radianos dado lados a b e c de um triangulo"
  [a b c]
  (math/to-radians (angulo a b c)))

(defn calc-angulo
  "TODO: Calcula o ângulo ∠A, dado A B C."
  [a b c]
  (math/to-degrees (angulo a b c)))

(defn calc-area
  "TODO: Calcula a área de um triângulo usando a formula de Heron."
  [a b c]
  (let [s (/ (calc-perimetro a b c) 2)]
    (math/sqrt (* (* (* s (- s a)) (- s b)) (- s c)))))

(defn calc-altura
  "TODO: Calcula altura de A, dado a AREA."
  [a area]
  (/ (* 2 area) a))

(defn equilateral?
  "TODO: Verifica se o triangulo é equilateral"
  [a b c]
  (== a b c))

(defn isosceles?
  "TODO: Verifica se pelo menos dois lados sao iguais."
  [a b c]
  (or (== a b) (== a c) (== b c)))

(defn escaleno?
  "TODO: Verifica se os lados dos triangulos sao diferentes entre si."
  [a b c]
  (not (== a b c)))

(defn retangulo?
  "TODO: Verifica se é um triangulo retangulo, cujos angulos são iguais a 90o.
  O resultado não é exato, dado que cada angulo é arredondado utilizando clojure.math/round."
  [a b c]
  (or (= (math/round (calc-angulo a b c)) 90) (= (math/round (calc-angulo b c a)) 90) (= (math/round (calc-angulo c b a)) 90)))

(defn obtuso?
  "TODO: Verifica se o triangulo é obtuso, tendo algum angulo >90o."
  [a b c]
  (if (not (retangulo? a b c))
    (or (> (math/round (calc-angulo a b c)) 90) (> (math/round (calc-angulo b c a)) 90) (> (math/round (calc-angulo c a b)) 90))
    false))

(defn agudo?
  "TODO: Verifica se o triangulo é obtuso, tendo algum angulo >90o."
  [a b c]
  (if (not (retangulo? a b c))
    (and (< (math/round (calc-angulo a b c)) 90) (< (math/round (calc-angulo b c a)) 90) (< (math/round (calc-angulo c a b)) 90))
    false))

;; Bla

(defn gerar-dados-completos
  [a b c]
  (let [area (calc-area a b c)]
        {:lados [a b c]
         :retagulo (retangulo? a b c)
         :obtuso (obtuso? a b c)
         :agudo (agudo? a b c)
         :escaleno (escaleno? a b c)
         :isosceles (isosceles? a b c)
         :equilateral (equilateral? a b c)
         :area area
         :altura [(calc-altura a area)
                  (calc-altura b area)
                  (calc-altura c area)]
         :angulos [(calc-angulo a b c)
                   (calc-angulo b c a)
                   (calc-angulo c a b)]}))

(comment
  (require 'clojure.pprint)
  (escaleno? 60 51.96152 30)
  (retangulo? 60 51.96152 30)
  (clojure.pprint/pprint (gerar-dados-completos 30 20 44))
  (clojure.pprint/pprint (gerar-dados-completos 60 51.96152 30))
  (clojure.pprint/pprint (gerar-dados-completos 15.14741 28.08887 30))
  )
