// Clase Empleado
public class Empleado {
    private String nombre;
    private int edad;
    private int sueldo;
    private int telefono;

    // Constructor
    public Empleado(String nombre, int edad, int sueldo, int telefono) {
        this.nombre = nombre;
        this.edad = edad;
        this.sueldo = sueldo;
        this.telefono = telefono;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    // Metodo extra para mostrar datos
    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Sueldo: " + sueldo);
        System.out.println("Teléfono: " + telefono);
    }

    public static void main(String[] args) {
        // Crear un main para que se vea que funciona toda la estructura , utiliza 3 instancias para luego ademas mostranos los resultados en pantalla
        Administrativo admin = new Administrativo("Laura", 35, 35000, 123456789, "Recursos Humanos", "Fijo");
        admin.mostrarDatos();
        admin.cambiarDepartamento("Finanzas");
        Administrativo admin2 = new Administrativo("Pedro", 30, 30000, 664123654, "Recursos Humanos", "temporal");
        System.out.println();
        admin2.mostrarDatos();


        System.out.println();

        Informatico info = new Informatico("Carlos", 29, 45000, 987654321, 0, new String[5]);
        info.añadirLenguajeDomina("Java");
        info.añadirLenguajeDomina("Python");
        info.mostrarDatos();

        System.out.println();

        Contable contable = new Contable("Marta", 40, 38000, 654123987, 20);
        contable.mostrarDatos();
        contable.updateCuentaMantenida();
    }
}

// Subclase Administrativo
class Administrativo extends Empleado {
    private String departamento;
    private String tipoDeContrato;

    // Constructor
    public Administrativo(String nombre, int edad, int sueldo, int telefono, String departamento, String tipoDeContrato) {
        super(nombre, edad, sueldo, telefono);
        this.departamento = departamento;
        this.tipoDeContrato = tipoDeContrato;
    }

    // Getters y Setters
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getTipoDeContrato() {
        return tipoDeContrato;
    }

    public void setTipoDeContrato(String tipoDeContrato) {
        this.tipoDeContrato = tipoDeContrato;
    }

    // Métodos extra de esta subclase
    public void cambiarDepartamento(String nuevoDepartamento) {
        this.departamento = nuevoDepartamento;
        System.out.println("Departamento cambiado a: " + nuevoDepartamento);
    }

    public void cambiarContrato(String nuevoContrato) {
        this.tipoDeContrato = nuevoContrato;
        System.out.println("Contrato cambiado a: " + nuevoContrato);
    }
}

// Subclase Informatico
class Informatico extends Empleado {
    private int numeroLenguajesDomina;
    private String[] lenguajesDomina;

    // Constructor
    public Informatico(String nombre, int edad, int sueldo, int telefono, int numeroLenguajesDomina, String[] lenguajesDomina) {
        super(nombre, edad, sueldo, telefono);
        this.numeroLenguajesDomina = numeroLenguajesDomina;
        this.lenguajesDomina = lenguajesDomina;
    }

    // Métodos extra de esta subclase
    public void añadirLenguajeDomina(String nuevoLenguaje) {
        System.out.println("Añadiendo el lenguaje: " + nuevoLenguaje);
        // Ejemplo simple para añadir al array
        if (numeroLenguajesDomina < lenguajesDomina.length) {
            lenguajesDomina[numeroLenguajesDomina] = nuevoLenguaje;
            numeroLenguajesDomina++;
        } else {
            System.out.println("No se pueden añadir más lenguajes.");
        }
    }
}

// Subclase Contable
class Contable extends Empleado {
    private int numCuentasManejadas;

    // Constructor
    public Contable(String nombre, int edad, int sueldo, int telefono, int numCuentasManejadas) {
        super(nombre, edad, sueldo, telefono);
        this.numCuentasManejadas = numCuentasManejadas;
    }

    // Metodo extra
    public void updateCuentaMantenida() {
        System.out.println("Actualizando cuenta mantenida.");
    }
}
