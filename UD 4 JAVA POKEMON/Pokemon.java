// paquete com.jemplo.pokemon 

package com.ejemplo.pokemon;

//importamos la clase arraylist del paquete java.util de la libreria de java para utilizarlo luego en la creacion de un array para el listado de los pokemon que escogen los jugadores

import java.util.ArrayList;
import java.util.Scanner;

// Super clase Pokemon

public class Pokemon {
	
	// declaracion de variable de acceso privado y tipo de dato string pero que despues fuera de la clase con los metodos getter y setter utilizaremos , es parte del concepto encapsulamiento. 
    
	private String nombre;
	
	// declaracion de variable de acceso privado y tipo de dato int que despues fuera de la clase con los metodos getter y setter utilizaremos , es parte del concepto encapsulamiento. 
    
	private int nivel;
	
	// declaracion de variable de acceso privado y tipo de dato int que despues fuera de la clase con los metodos getter y setter utilizaremos , es parte del concepto encapsulamiento. 
    
	private int energia;
    
    // Constructor sin datos
    
	public Pokemon() {
    
		// Utilizamos this para llamar al atributo nombre de la instancia Pokemon 
    	
		// Esto asegura que estamos asignando valores a los atributos `nombre`, `nivel` y `energia` de la instancia que se está creando.
        
		this.nombre = "";
        this.nivel = 0;
        this.energia = 0;
    }
    
	// Constructor con datos publico para que sea accesible por todas las clases y que le tenemos que pasar los valores 'nombre' 'nivel' y 'energia'
    // que se los pasaremos al crear los objetos de cada pokemon en el main ()
    
	public Pokemon(String nombre, int nivel, int energia) {
    	
		// Utilizamos this para llamar al atributo nombre de la instancia Pokemon 
    	// Esto asegura que estamos asignando valores a los atributos `nombre`, `nivel` y `energia` de la instancia que se está creando.
        
		this.nombre = nombre;
        this.nivel = nivel;
        this.energia = energia;
    }
    
	// Getters y Setters y en los que como en setnivel evaluamos que no sean negativos 
    // Devuelve un String que contiene el valor actual de nombre y es accesible desde cualquier clase
    
	public String getNombre() {
        return nombre;
    }
    
	// Setter para asignar un valor a la variable nombre.
    // Recibe un parámetro de tipo String y lo asigna a la variable nombre.
    
	public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
	// Devuelve el valor que contiene 'nivel' con un entero accesible desde cualquier clase
    
	public int getNivel() {
        return nivel;
    }

    // Este setter verifica que el valor proporcionado a 'nivel' no sea negativo. 
    // Si el valor es negativo, lanza una excepción IllegalArgumentException con un mensaje explicativo.
    // Si el setter es llamado en el objeto creado en el main() con un valor negativo, se mostrará 
    // el mensaje de la excepción en pantalla.
    
	public void setNivel(int nivel) {
    
		if (nivel < 0) {
            throw new IllegalArgumentException("El nivel no puede ser negativo.");
        }
        this.nivel = nivel;
    }
    
    // Devuelve el valor que contiene 'nivel' con un entero accesible desde cualquier clase
    
	public int getEnergia() {
        return energia;
    }
    
    // Este setter verifica que el valor proporcionado a 'energia' no sea negativo. 
    // Si el valor es negativo, lanza una excepción IllegalArgumentException con un mensaje explicativo.
    // Si el setter es llamado en el objeto creado en el main() con un valor negativo, se mostrará 
    // el mensaje de la excepción en pantalla. Se usa void por que no tiene que devolver ningun mensaje
    
	public void setEnergia(int energia) {
        if (energia < 0) {
            throw new IllegalArgumentException("La energía no puede ser negativa.");
        }
        this.energia = energia;
    }
    
	// objeto que no devuelve ningun valor pero que saca por pantalla el nombre de un pokemon y realiza un ataque basico
    public void atacar() {
        System.out.println("\n" + nombre + " realiza un ataque básico.");
    }

    // Clase publica PokemonHielo es una clase hija de la clase Pokemon 
    
    public static class PokemonHielo extends Pokemon {
        
    	// declaramos los atributos  que vamos a usar en esta clase hija pero que sean private 
    	
    	private String habilidadPokHielo;
        private int fuerzaPokHielo;

        // constructor publico PokemonHielo publico y sin valores
        public PokemonHielo() {
            super();
            this.habilidadPokHielo = "";
            this.fuerzaPokHielo = 0;
        }

        // constructor publico PokemonHielo pero con los valores definidos
        public PokemonHielo(String nombre, int nivel, int energia, String habilidadPokHielo, int fuerzaPokHielo) {
            super(nombre, nivel, energia);
            this.habilidadPokHielo = habilidadPokHielo;
            this.fuerzaPokHielo = fuerzaPokHielo;
        }

        // Sobrescribe el método atacar para implementar la lógica específica de un Pokémon de tipo Agua.
        // Imprime la habilidad especial y la fuerza de ataque correspondiente.
        
        @Override
        public void atacar() {
            System.out.println("\nLa habilidad especial de " + getNombre() + " es " + habilidadPokHielo + "!");
            System.out.println("Su fuerza de ataque es: " + fuerzaPokHielo);
        }
    }

    // Clase publica PokemonAgua clase hija de la clase Pokemon	
    public static class PokemonAgua extends Pokemon {
    	// definiendo los atributos de esta clase
    	private String habilidadPokAgua;
        private int fuerzaPokAgua;

        // constructor publico PokemonAgua publico y sin valores
        public PokemonAgua() {
            super();
            this.habilidadPokAgua = "";
            this.fuerzaPokAgua = 0;
        }

       // constructor publico PokemonAgua pero con los valores definidos
        public PokemonAgua(String nombre, int nivel, int energia, String habilidadPokAgua, int fuerzaPokAgua) {
            super(nombre, nivel, energia);
            this.habilidadPokAgua = habilidadPokAgua;
            this.fuerzaPokAgua = fuerzaPokAgua;
        }

        // Sobrescribe el método atacar para implementar la lógica específica de un Pokémon de tipo Agua.
        // Imprime la habilidad especial y la fuerza de ataque correspondiente.
        
        @Override
        public void atacar() {
            System.out.println("\nLa habilidad especial de " + getNombre() + " es " + habilidadPokAgua + "!");
            System.out.println("Su fuerza de ataque es: " + fuerzaPokAgua);
        }
    }

    // Clase PokemonFuego clase hija de la clase Pokemon
    public static class PokemonFuego extends Pokemon {
    	// definiendo los atributos de esta clase
    	private String habilidadPokFuego;
        private int fuerzaPokFuego;

        // constructor publico PokemonFuego publico y sin valores
        public PokemonFuego() {
            super();
            this.habilidadPokFuego = "";
            this.fuerzaPokFuego = 0;
        }

        // constructor publico PokemonFuego pero con los valores definidos
        public PokemonFuego(String nombre, int nivel, int energia, String habilidadPokFuego, int fuerzaPokFuego) {
            super(nombre, nivel, energia);
            this.habilidadPokFuego = habilidadPokFuego;
            this.fuerzaPokFuego = fuerzaPokFuego;
        }

        // Sobrescribe el método atacar para implementar la lógica específica de un Pokémon de tipo Fuego.
        // Imprime la habilidad especial y la fuerza de ataque correspondiente.
        
        @Override
        public void atacar() {
            System.out.println("\nLa habilidad especial de " + getNombre() + " es " + habilidadPokFuego + "!");
            System.out.println("Su fuerza de ataque es: " + fuerzaPokFuego);
        }
    }
    // Clase Entrenador
    public static class Entrenador {
        // definiendo los atributos de esta clase 
    	private String nombre;
        private int experiencia;
        private Pokemon pokemonPrincipal;

        // constructor publico Entranador publico y sin valores
        public Entrenador() {
            this.nombre = "";
            this.experiencia = 0;
        }
        // constructor publico Entrenador pero con los valores definidos 
        public Entrenador(String nombre, int experiencia) {
            this.nombre = nombre;
            this.experiencia = experiencia;
        }

        // getter para devolver el valor que tiene el atributo nombre
        public String getNombre() {
            return nombre;
        }
        
        // getter para devolver el valor que tiene el atributo experiencia
        public int getExperiencia() {
            return experiencia;
        }

        // getter para devolver el valor que tiene el atributo PokemonPrincipal 
        public Pokemon getPokemonPrincipal() {
            return pokemonPrincipal;
        }

        public void elegirPokemon(ArrayList<Pokemon> opciones) {
        	
        	// Creamos un objeto Scanner para leer desde la consola que jugador quiere
            
        	Scanner scanner = new Scanner(System.in);
            
            // Preguntamos que jugador de pokemon quiere el jugador 
            
            System.out.println("\n" + nombre + ", elige el número del Pokémon que quieres:");
            
            // Mostramos al jugador todas las opciones disponibles en el ArrayList 'opciones'
            
            for (int i = 0; i < opciones.size(); i++) {
            
            	System.out.println((i + 1) + ". " + opciones.get(i).getNombre() + " (Nivel: " + opciones.get(i).getNivel() + ")");
            }
            
            // Leemos la elección del jugador desde la consola (el numero de la posicion en el array del Pokémon seleccionado) y lo guardamos en la variable eleccion
            
            int eleccion = scanner.nextInt();
            
            // Asignamos el Pokémon elegido como el principal del jugador
            
            pokemonPrincipal = opciones.get(eleccion - 1);
            
            // Eliminamos el Pokémon seleccionado de la lista de opciones para que no pueda ser escogido de nuevo
            
            opciones.remove(eleccion - 1);
            
            // Mostramos al jugador de su elección
            
            System.out.println(nombre + " has elegido a " + pokemonPrincipal.getNombre() + "!");
        }
    }

    // Clase Combate declarada como pública y estática para facilitar su acceso desde el método main 
    // sin necesidad de instanciar la clase contenedora.
    
    public static class Combate {
    	
    	// Atributos privados que representan a los dos entrenadores participantes en el combate.
        
    	private Entrenador entrenador1;
        private Entrenador entrenador2;
        
        // constructor
        // Se inicializa con dos entrenadores como parámetros.
        
        public Combate(Entrenador entrenador1, Entrenador entrenador2) {
        	
        	// Asignamos los entrenadores recibidos al combate.
        	
        	this.entrenador1 = entrenador1;
            this.entrenador2 = entrenador2;
        }
        // No devuelve ningún valor, pero imprime los resultados del combate en la consola.
        
        public void iniciarCombate() {
        	
        	// Obtenemos los Pokémon principales de cada entrenador.
            
        	Pokemon pokemon1 = entrenador1.getPokemonPrincipal();
            Pokemon pokemon2 = entrenador2.getPokemonPrincipal();
            
            // Mostramos en pantalla el inicio del combate, incluyendo los nombres de los Pokémon.
            
            System.out.println("\n¡Empieza el combate entre " + pokemon1.getNombre() + " y " + pokemon2.getNombre() + "!");
            
            // comparamos el nivel de un pokemon con el otro y depende de los resultados mostramos una opcion u otra en pantalla
            
            if (pokemon1.getNivel() > pokemon2.getNivel()) {
            
            	// Si el ganador es el pokemon 1 lo mostramos esto en pantalla
                
            	System.out.println("\nEl ganador es: " + pokemon1.getNombre() + " de " + entrenador1.getNombre() + "!");
            
            } else if (pokemon1.getNivel() < pokemon2.getNivel()) {
            	
            	// Si el ganador es el pokemon 2 lo mostramos esto en pantalla
            	
            	System.out.println("\nEl ganador es: " + pokemon2.getNombre() + " de " + entrenador2.getNombre() + "!");
            
            } else {
            	
            	//si sale empate en el resultado por que ni gana uno ni gana otro la ultima opcion es empate y lo mostramos en pantalla
                
            	System.out.println("\n¡El combate termina en empate!");
            }
        }
    }

    // Declaración del método main: es público y asi se puede acceder a él desde cualquier lugar,
    // y es estático para poder ejecutarse sin crear una instancia de la clase,
    // y no devuelve ningún valor porque su propósito es iniciar la ejecución del programa.
    
    public static void main(String[] args) {
    	
    	// Creacion de un logo con codigo ASCI para la presentación del juego 
    	
    	System.out.println("");
    	System.out.println(" _|_|_|    _|_|_  |      | |------  ||    ||   _|_|_  |     | ");
    	System.out.println(" |     |  |     | |     |  |        | |  | |  |     | | |   | ");
    	System.out.println(" |____|   |     | |___|    |----    |  ||  |  |     | |  |  | ");
    	System.out.println(" |        |     | |     |  |        |      |  |     | |   | | ");
    	System.out.println(" |         |_|_|  |      | |------  |      |   |_|_|  |     | ");
    	System.out.println("");

    	// Creamos los objetos de cada pokemos con sus caracteristicas
        
    	PokemonHielo articuno = new PokemonHielo("Articuno", 198, 180, "Rayo hielo", 65);
        PokemonAgua seaking = new PokemonAgua("Seaking", 172, 160, "Viento de hielo", 25);
        PokemonFuego charizard = new PokemonFuego("Charizard", 200, 190, "Llamarada", 70);

        // Creamos una lista con las  opciones de Pokemon que tenemos y asi luego podre cuando escojan
        // cada entrenador escoger su pokemon, lo eliminare del listado de opciones y asi no se repetiran
        // y cada entrenador tendra su pokemon difernte .
        
        ArrayList<Pokemon> opciones = new ArrayList<>();
        opciones.add(articuno);
        opciones.add(seaking);
        opciones.add(charizard);

        // Creamos los objetos de los entrenadores con los datos de su npmbre y experiencia
        
        Entrenador ash = new Entrenador("Ash", 100);
        Entrenador misty = new Entrenador("Misty", 120);

        ash.elegirPokemon(opciones);
        misty.elegirPokemon(opciones);

        // Creamos el objeto del combate 
        
        Combate combate = new Combate(ash, misty);
        
        // Y aqui lo iniciamos ...
        
        combate.iniciarCombate();
    }
}
