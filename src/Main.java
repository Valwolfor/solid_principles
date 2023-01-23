import single_responsability_and_SOLID.controller.UsuarioBuilder;
import single_responsability_and_SOLID.controller.Usuarios;
import single_responsability_and_SOLID.data_access.UsuariosDB;
import single_responsability_and_SOLID.data_access.UsuariosDBData;
import single_responsability_and_SOLID.data_access.UsuariosDBMemory;
import single_responsability_and_SOLID.data_access.UsuariosDBTxt;
import single_responsability_and_SOLID.services.UsuariosNivel;

/**
 * @author : @vroman
 * OpenBootCamp code.
 * @see <a href="https://github.com/Open-Bootcamp/java_avanzado/tree/master/sesiones_19_20_21/src/Sesion21/Inicial">...</a>
 */
public class Main {
    /**
     * La arquitectura limplia busca que los programa sean lo menos acomplados, lo más faciles de
     * modificar o ampliar. La arquitectura limplia implementa un método por capas en dónde cáda
     * capa solo tendrá accesos a sus capa directamente superior e inferior, pero nunca más de
     * dos capas. Para aplicar la mejor forma se debe aplicar los principios SOLID.
     *
     * @param args contiene la clave valor del usuario por defecto que se debe agregar.
     * @author Valwolfor
     */
    public static void main(String[] args) {
        UsuariosDB usuariosDB = factoryUsuarioDB("texto");
        Usuarios usuarios = new Usuarios(usuariosDB);

        usuarios.addUser(new UsuarioBuilder(args[1])
                .setNombre(args[3])
                .setApellidos(args[5])
                .setEmail(args[7])
                .setNivelDeAcceso(Integer.parseInt(args[9]))
                .getUsuario()
        );

        System.out.println("Se creo el primer usuario, el por argumentos.");

        usuarios.addUser(new UsuarioBuilder("openbootcamp")
                .setNombre("Open")
                .setApellidos("Bootcamp")
                .setEmail("ejemplos@open-bootcamp.com")
                .setNivelDeAcceso(10)
                .getUsuario()
        );

        System.out.println("Se creo segundo usuario por método.");

        usuarios.addUser(new UsuarioBuilder("openbootcamp2")
                .setNombre("Open2")
                .setApellidos("Bootcamp2")
                .setEmail("ejemplos2@open-bootcamp.com")
                .setNivelDeAcceso(8)
                .getUsuario()
        );

        System.out.println("Se creo tercer usuario por método.");

        usuarios.addUser(new UsuarioBuilder("openbootcamp3")
                .setNombre("Open3")
                .setApellidos("Bootcamp3")
                .setEmail("ejemplos3@open-bootcamp.com")
                .setNivelDeAcceso(1)
                .getUsuario()
        );

        System.out.println("Se creo cuarto usuario por método.");

        usuarios.addUser(new UsuarioBuilder("openbootcamp3")
                .setNombre("Open3")
                .setApellidos("Bootcamp3")
                .setEmail("ejemplos3@open-bootcamp.com")
                .setNivelDeAcceso(5)
                .getUsuario()
        );

        System.out.println("Se intentó agregar cuarto usuario de nuevo.");

        usuarios.getAll();

        System.out.println("\n" + usuarios.getUser("Valwolfor").getNombreUsuario() + "\n");

        usuarios.deleteUser("openbootcamp");

        usuarios.getAll();

        usuarios.deleteUser("openbootcamp");

        UsuariosNivel usuariosLvl = new UsuariosNivel(usuarios);
        System.out.println("=============================================");

        usuariosLvl.isAdmin(usuarios.getUser("Valwolfor"));
        usuariosLvl.isStudent(usuarios.getUser("openbootcamp2"));
        usuariosLvl.isGuest(usuarios.getUser("openbootcamp3"));


        mostrarEstadisticas(usuariosDB);

    }

    public static void mostrarEstadisticas(UsuariosDB usarioDB) {
        if (usarioDB instanceof UsuariosDBMemory) {
            System.out.println("Los datos desde memoria");
            ((UsuariosDBMemory) usarioDB).getData();
        } else {
            System.out.println("Los datos desde archivo de texto");
            ((UsuariosDBTxt) usarioDB).getData();
        }
    }

    public static UsuariosDB factoryUsuarioDB(String metodo) {
        if (metodo.equalsIgnoreCase("memoria")) {
            System.out.println("Se selecionó el método por memoria.");
            return new UsuariosDBMemory();
        } else if (metodo.equalsIgnoreCase("texto")) {
            System.out.println("Se selecionó el método por archivo de texto.");
            return new UsuariosDBTxt();
        } else {
            System.out.println("Ingrese un método valido, por favor.");
        }
        return null;
    }
}

