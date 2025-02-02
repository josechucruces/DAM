Algoritmo cifras_aleatorias
    // Declarar variables
    Definir num1, cifras Como Entero
    Definir texto, respuesta Como Cadena
	
    // Iniciar un bucle para repetir el proceso
    Repetir
        // Repetir hasta que se ingrese un número válido
        Repetir
            Escribir "Introduzca un número entre 0 y 99.999:"
            Leer num1
            
            // Verificar si está fuera de rango
            Si num1 < 0 O num1 > 99999 Entonces
                Escribir "Número fuera de rango, intente nuevamente."
            FinSi
        Hasta Que num1 >= 0 Y num1 <= 99999
        
        // Convertir el número a texto y contar las cifras
        texto <- ConvertirATexto(num1)
        cifras <- Longitud(texto)
        
        // Salida: Mostrar el número y la cantidad de cifras
        Escribir "El número introducido es: ", num1
        Escribir "El número tiene ", cifras, " cifras."
		
        // Preguntar si desea continuar
        Escribir "¿Desea continuar? (Presione s para continuar, cualquier otra tecla para salir):"
        Leer respuesta
    Hasta Que respuesta <> "s"
    
FinAlgoritmo