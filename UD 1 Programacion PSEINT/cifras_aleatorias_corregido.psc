Algoritmo cifras_aleatorias
    // Declarar variables para almacenar el n�mero ingresado, la cantidad de cifras,
    // y las cadenas de texto para la conversi�n y respuesta del usuario.
    Definir num1, cifras Como Entero
    Definir texto, respuesta Como Cadena
	
    // Iniciar un bucle para repetir el proceso de pedir y validar n�meros hasta que el usuario decida salir.
    Repetir
        // Bucle para asegurar que el n�mero ingresado est� dentro del rango permitido (0 a 99,999).
        Repetir
            // Solicitar al usuario que ingrese un n�mero entre 0 y 99,999.
            Escribir "Introduzca un n�mero entre 0 y 99.999:"
            Leer num1
            
            // Verificar si el n�mero est� fuera del rango especificado.
            Si num1 < 0 O num1 > 99999 Entonces
                // Si el n�mero no es v�lido, mostrar un mensaje de error.
                Escribir "N�mero fuera de rango, intente nuevamente."
            FinSi
			// El bucle se repetir� hasta que el n�mero ingresado est� dentro del rango permitido.
        Hasta Que num1 >= 0 Y num1 <= 99999
        
        // Convertir el n�mero ingresado en texto para poder contar el n�mero de cifras.
        texto <- ConvertirATexto(num1)
        // Contar la cantidad de cifras que tiene el n�mero convertido a texto.
        cifras <- Longitud(texto)
        
        // Mostrar al usuario el n�mero ingresado y la cantidad de cifras que tiene.
        Escribir "El n�mero introducido es: ", num1
        Escribir "El n�mero tiene ", cifras, " cifras."
		
        // Preguntar al usuario si desea continuar o finalizar el programa.
        Escribir "�Desea continuar? (Presione s para continuar, cualquier otra tecla para salir):"
        Leer respuesta
		// El bucle principal se repetir� si el usuario ingresa "s", de lo contrario, terminar�.
    Hasta Que respuesta <> "s"
    
FinAlgoritmo