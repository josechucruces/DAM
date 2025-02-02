Algoritmo cifras_aleatorias
    // Declarar variables para almacenar el número ingresado, la cantidad de cifras,
    // y las cadenas de texto para la conversión y respuesta del usuario.
    Definir num1, cifras Como Entero
    Definir texto, respuesta Como Cadena
	
    // Iniciar un bucle para repetir el proceso de pedir y validar números hasta que el usuario decida salir.
    Repetir
        // Bucle para asegurar que el número ingresado está dentro del rango permitido (0 a 99,999).
        Repetir
            // Solicitar al usuario que ingrese un número entre 0 y 99,999.
            Escribir "Introduzca un número entre 0 y 99.999:"
            Leer num1
            
            // Verificar si el número está fuera del rango especificado.
            Si num1 < 0 O num1 > 99999 Entonces
                // Si el número no es válido, mostrar un mensaje de error.
                Escribir "Número fuera de rango, intente nuevamente."
            FinSi
			// El bucle se repetirá hasta que el número ingresado esté dentro del rango permitido.
        Hasta Que num1 >= 0 Y num1 <= 99999
        
        // Convertir el número ingresado en texto para poder contar el número de cifras.
        texto <- ConvertirATexto(num1)
        // Contar la cantidad de cifras que tiene el número convertido a texto.
        cifras <- Longitud(texto)
        
        // Mostrar al usuario el número ingresado y la cantidad de cifras que tiene.
        Escribir "El número introducido es: ", num1
        Escribir "El número tiene ", cifras, " cifras."
		
        // Preguntar al usuario si desea continuar o finalizar el programa.
        Escribir "¿Desea continuar? (Presione s para continuar, cualquier otra tecla para salir):"
        Leer respuesta
		// El bucle principal se repetirá si el usuario ingresa "s", de lo contrario, terminará.
    Hasta Que respuesta <> "s"
    
FinAlgoritmo