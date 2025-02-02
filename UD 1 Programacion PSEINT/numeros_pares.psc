Algoritmo numeros_pares
    Definir dividendo, divisor, resto Como Entero
    Definir respuesta Como Cadena
    respuesta <- "si"
    
    Repetir
        Escribir "Introduzca un número entre 0 y 99.999:"
        Leer dividendo
        divisor <- 2
        resto <- dividendo mod divisor
        
        Si resto <- 0 Entonces
            Escribir "El número es par."
        Sino
            Escribir "El número es impar."
        FinSi
        
        Escribir "¿Quieres seguir jugando? Responde si para continuar u otra respuesta para salir:"
        Leer respuesta
    Hasta Que respuesta <> "si"
FinAlgoritmo