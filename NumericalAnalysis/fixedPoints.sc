import math.abs
//Si g es una funcion continua en el intervalo [a, b] y para todo x en [a, b]
//se cumple que g(x) pertenece [a, b] entonces g tiene un punto fijo en [a, b].

//Si ademas, para todo x en (a, b) se cumple que g prima(x) existe en (a, b) y
//abs(g prima(x)) <= k < 1, entonces g tiene un unico punto fijo p en [a, b], es
// decir que la velocidad de cambio tiene que ser menor a 1
//

//El teorema no garantiza la existencia de un punto fijo en [a, b],
// de modo que se requiere que la funcion en el intervalo [a, b]
// quede completamente dentro

val tolerancia = 1e-12

def isCloseEnough(x: Double, y: Double): Boolean =
  if (x == 0.0) {print("Divide by zero"); false} else
  abs((x - y) / x) / x < tolerancia

def fixedPoint(f: Double => Double)(firstGuess: Double):Double = {
  def iterate(guess: Double): Double = {
    val next = f(guess)
    println(next)
    if(isCloseEnough(guess, next)) next
    else iterate(next)
  }
  iterate(firstGuess)
}

fixedPoint(x => -math.sqrt((x * math.exp(x)) - (5 * x) - 3)) (-4.5)
fixedPoint(x => x*x-4)(2)

def newton(f: Double => Double)(firstGuess: Double): Double =
  fixedPoint(f) (firstGuess)

//Teorema del intervalo cerrado

def sqrt(x: Double): Double = fixedPoint(averageDamp(y => x / y))(1)

//AverageDamp is a function that receives a function and returns another function
def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2
print("-----------------------------------------------------------------")

sqrt(81)

//Generalmente se usa busquedaIncremental prara ver el intervalo
//Biseccion para mejorarlo y Newton para encontrarlo
//Newton
//x0
//xn = xn-1 - f(xn-1)/ f'(xn-1)



newton(x => x - (((x*math.exp(x)) - math.pow(x, 2) - 5*x - 3)/((x*math.exp(x)) - math.exp(x) - (2*x) - 5)))(1)