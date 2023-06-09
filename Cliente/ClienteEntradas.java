import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
import java.lang.Thread;

import java.lang.ProcessBuilder;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

class ClienteEntradas {

	private static int escenario;
	private static int eleccion;

	static public void main(String args[]) {

		if (args.length != 4) {
			System.err.println("Uso: ClienteEntrada hostregistroEntradas hostregistroEventos numPuertoSrvEntradas numPuertoSrvEventos");
			return;
		}

		if (System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());

		try {
			String ruta;
			String rutaFichero;
			String nombre = "";
			String apellido = "";
			String DNI = "";
			int idEvento = 0;
			int opcion = 0;
			int numEntradas = 0;
			int idCompra = 0;
			String identEntrada = "";
			String salto = "";
			int esAdmin = 0;
			int tipoEstadio = 0;
			List<Evento> listaEventosDisponibles = new ArrayList<Evento>();
			List<Entrada> listaEntradasCompradas = new ArrayList<Entrada>();

			String nombreArtista = "";
			String ciudadEvento = "";
			String recintoEvento = "";
			String fechaEvento = "";

			Entrada entrada = new Entrada();
			Evento evento = new Evento();
			System.out.println("Creando conexion con servidor entradas");
			ServicioEntradas srv = (ServicioEntradas) Naming.lookup("//" + args[0] + ":" + args[2] + "/Entrada");
			System.out.println("Conexion creada");
			FabricaServicioEvento fabricaE = (FabricaServicioEvento) Naming
					.lookup("//" + args[1] + ":" + args[3] + "/Eventos");
			ServicioEventos srvEvent = fabricaE.crearServicioEvento();
			Scanner ent = new Scanner(System.in);
			System.out.println("\n\n==================================");
			System.out.println("| SERVICIO DE VENTAS DE ENTRADAS |");
			System.out.println("==================================");

			System.out.println("\n\n==================================");
			System.out.println("|    ¿ES USTED ADMINISTRADOR?    |");
			System.out.println("==================================");

			System.out.print("Introduzca 1 si es admin y 2 si es un usuario\n> ");

			if (ent.hasNextLine())
				esAdmin = Integer.parseInt(ent.nextLine());

			if (esAdmin == 1) {
				System.out.println("\n\n==================================");
				System.out.println("|    BIENVENIDO ADMINISTRADOR    |");
				System.out.println("==================================");

				do {
					System.out.println("\nAdministrador, elija que quiere hacer: \n");
					System.out.println("----------------------------------");
					System.out.println("(1)>       CREAR UN EVENTO        ");
					System.out.println("----------------------------------");
					System.out.println("(2)>       BORRAR UN EVENTO       ");
					System.out.println("----------------------------------");
					System.out.println("(3)>  LISTAR EVENTOS DISPONIBLES  ");
					System.out.println("----------------------------------");
					System.out.println("(4)>    LISTAR DETALLES EVENTO    ");
					System.out.println("----------------------------------");
					System.out.println("(5)>           CANCELAR           ");
					System.out.println("----------------------------------");
					// System.out.println("") //AÑADIR EL MENU 5 DE LISTAR TODAS LAS ENTRADAS

					System.out.print("Opción > ");
					if (ent.hasNextLine())
						opcion = Integer.parseInt(ent.nextLine());
					System.out.print("\n");

					switch (opcion) {
						case 1: {

							System.out.println("=======================");
							System.out.println("|   CREAR UN EVENTO   |");
							System.out.println("=======================\n\n");

							System.out.print("> ¿Cuál es el nombre del artista?\n> ");
							if (ent.hasNextLine())
								nombreArtista = ent.nextLine();
							System.out.print("\n");

							System.out.print("> ¿En que ciudad será el evento?\n> ");
							if (ent.hasNextLine())
								ciudadEvento = ent.nextLine();
							System.out.print("\n");

							System.out.print("> ¿En que recinto se celebrará el evento?\n> ");
							if (ent.hasNextLine())
								recintoEvento = ent.nextLine();
							System.out.print("\n");

							System.out.print("> ¿En que fecha será el evento? (aaaa-mm-dd)\n> ");
							if (ent.hasNextLine())
								fechaEvento = ent.nextLine();
							System.out.print("\n");

							System.out.print("> ¿Cuántas entradas habrá disponibles?\n> ");
							if (ent.hasNextLine())
								numEntradas = Integer.parseInt(ent.nextLine());
							System.out.print("\n");

							System.out.print("> ¿Que tipo de estadio será?\n");
							System.out.print("> Estadio 1, 2 o 3\n> ");
							
							boolean tipoEstadioCorrecto = false;
							
							while (!tipoEstadioCorrecto){
								tipoEstadio = 0;
								if (ent.hasNextLine())
									tipoEstadio = Integer.parseInt(ent.nextLine());

								if(tipoEstadio > 0 && tipoEstadio < 4){
									tipoEstadioCorrecto = true;
								} else {
									System.out.print("> Tipo de estadio seleccionado erróneo\n");
									System.out.print("> Seleccione estadio 1, 2 o 3\n> ");
								}
							}
								
							System.out.print("\n");

							boolean resultadoCreaEvento;
							resultadoCreaEvento = srvEvent.crearEvento(nombreArtista, fechaEvento, fechaEvento,
									ciudadEvento, numEntradas, tipoEstadio);

							Thread.sleep(2000);

							if (resultadoCreaEvento == true) {
								System.out.println("> ¡¡EVENTO CREADO CON ÉXITO!!\n");
							} else {
								System.out.println("> ¡¡ERROR AL CREAR EVENTO!!\n");
							}

							System.out.print("> Pulse enter para continuar\n> ");
							if (ent.hasNextLine()) {
								salto = ent.nextLine();
								break;
							}

						}

						case 2: {

							System.out.println("========================");
							System.out.println("|   BORRAR UN EVENTO   |");
							System.out.println("========================\n\n");

							System.out.print("> Introduzca el identificador del evento que desea borrar\n> ");
							if (ent.hasNextLine())
								idEvento = Integer.parseInt(ent.nextLine());
							System.out.print("\n");

							if ((srvEvent.borrarEvento(idEvento))) {
								System.out.println("> ¡¡EVENTO BORRADO CON CON ÉXITO!!\n");
								System.out.println("> ¡¡ENTRADAS ASOCIADAS AL EVENTO BORRADAS CON ÉXITO!!\n");
							}

							else {
								System.out.println("> ERROR AL BORRAR EL EVENTO.");
								System.out.println(
										"> POR FAVOR, INTENTELO DE NUEVO MÁS TARDE O CONSULTE AL ADMINISTRADOR\n");
							}

							System.out.print("> Pulse enter para continuar\n> ");
							if (ent.hasNextLine()) {
								salto = ent.nextLine();
								break;
							}

						}
						case 3: {

							System.out.println("==============================");
							System.out.println("| LISTAR EVENTOS DISPONIBLES |");
							System.out.println("==============================\n\n");

							System.out.println("> Se muestran todos los eventos actualmente disponibles");
							listaEventosDisponibles = srvEvent.listarEventos();

							Iterator i = listaEventosDisponibles.iterator();
							System.out.println("--------------------------------------------");
							while (i.hasNext()) {
								Evento event = (Evento) i.next();
								System.out.println("--------------------------------------------");
								System.out.println("> ID DEL EVENTO: " + event.getId());
								System.out.println("> NOMBRE DEL ARTISTA: " + event.getArtista());
								System.out.println("> FECHA DEL EVENTO: " + event.getFecha());
								System.out.println("> CIUDAD DONDE SE CELEBRA: " + event.getCiudad());
								System.out.println("> LUGAR DEL EVENTO: " + event.getLugar());
								System.out.println("> NUMERO DE ENTRADAS DISPONIBLES: " + event.getEntradas());
								System.out.println("> TIPO DE ESTADIO: " + event.getTipoEstadio());
								System.out.println("--------------------------------------------");
							}
							System.out.println("--------------------------------------------");
							System.out.print("\n");

							System.out.print("> Pulse enter para continuar\n> ");
							if (ent.hasNextLine()) {
								salto = ent.nextLine();
								break;
							}

						}

						case 4: {

							System.out.println("=================================");
							System.out.println("| LISTAR DETALLES DE UN EVENTO  |");
							System.out.println("=================================\n\n");

							System.out.println("> Introduzca el identificador del evento que desea mostrar");

							if (ent.hasNextLine())
								idEvento = Integer.parseInt(ent.nextLine());
							System.out.print("\n");

							evento = srvEvent.listarDetalleEvento(idEvento);

							if ((evento.getId()) != idEvento) {
								System.out.println("> NO EXISTE UN EVENTO CON ESE IDENTIFICADOR");
							} else {
								System.out.println("--------------------------------------------");
								System.out.println("> ID DEL EVENTO: " + evento.getId());
								System.out.println("> NOMBRE DEL ARTISTA: " + evento.getArtista());
								System.out.println("> FECHA DEL EVENTO: " + evento.getFecha());
								System.out.println("> CIUDAD DONDE SE CELEBRA: " + evento.getCiudad());
								System.out.println("> LUGAR DEL EVENTO: " + evento.getLugar());
								System.out.println("> NUMERO DE ENTRADAS DISPONIBLES: " + evento.getEntradas());
								System.out.println("> TIPO DE ESTADIO: " + evento.getTipoEstadio());
								System.out.println("--------------------------------------------");
							}

							System.out.print("\n");

							System.out.print("> Pulse enter para continuar\n> ");
							if (ent.hasNextLine()) {
								salto = ent.nextLine();
								break;
							}

						}

						case 5: {

							System.out.println("> SALIENDO DEL SERVICIO DE EVENTOS...");
							Thread.sleep(2000);
							return;
						}

						default: {
							System.out.println("> OPCIÓN NO VÁLIDA\n");
							System.out.print("> Pulse enter para continuar\n> ");

							if (ent.hasNextLine()) {
								salto = ent.nextLine();
								break;
							}
						}
					}

				} while (true);

				// COMIENZA EL MENU USUARIO
			} else {

				System.out.print("> Introduzca su nombre \n> ");
				if (ent.hasNextLine())
					nombre = ent.nextLine();

				System.out.print("> Introduzca su apellido \n> ");
				if (ent.hasNextLine())
					apellido = ent.nextLine();
				nombre = nombre + " " + apellido;

				System.out.print("> Introduza su DNI \n> ");
				if (ent.hasNextLine())
					DNI = ent.nextLine();
				System.out.print("\n");

				do {
					System.out.println("\n" + nombre + ", elija que quiere hacer: \n");
					System.out.println("----------------------------------");
					System.out.println("(1)>     COMPRAR UNA ENTRADA      ");
					System.out.println("----------------------------------");
					System.out.println("(2)>     CANCELAR UNA ENTRADA     ");
					System.out.println("----------------------------------");
					System.out.println("(3)>  LISTAR EVENTOS DISPONIBLES  ");
					System.out.println("----------------------------------");
					System.out.println("(4)> LISTAR MIS ENTRADAS COMPRADAS");
					System.out.println("----------------------------------");
					System.out.println("(5)>   SELECCIONAR MIS ASIENTOS   ");
					System.out.println("----------------------------------");
					System.out.println("(6)>           CANCELAR           ");
					System.out.println("----------------------------------");

					System.out.print("Opción > ");
					if (ent.hasNextLine())
						opcion = Integer.parseInt(ent.nextLine());
					System.out.print("\n");

					switch (opcion) {
						case 1: {

							System.out.println("=======================");
							System.out.println("| COMPRAR UNA ENTRADA |");
							System.out.println("=======================\n\n");

							System.out.print("> Seleccione el evento al que desea acudir\n> ");
							if (ent.hasNextLine())
								idEvento = Integer.parseInt(ent.nextLine());
							System.out.print("\n");
							System.out.print("> Seleccione el número de entradas que desea comprar\n> ");
							if (ent.hasNextLine())
								numEntradas = Integer.parseInt(ent.nextLine());
							System.out.print("\n");
							Random ale = new Random();
							int grada = ale.nextInt(2) + 1;
							System.out.println("> Se ha seleccionado la grada " + grada + " por defecto\n");

							entrada = srv.comprarEntrada(idEvento, numEntradas, nombre, DNI, grada); // Pondría para
																								// comprarEntrada q el
																								// valor devuelto sea un
																								// boolean para
																								// comprobar si se ha
																								// comprado o no.
																								// También añadiría un
																								// parámetro para el
																								// apellido
							/*
							 * Entrada entrada1 = new Entrada();
							 * entrada1.setId(1);
							 * entrada1.setArtista("David Bisbal");
							 * entrada1.setFecha("14/08/23");
							 * entrada1.setLugar("Plaza de Toros");
							 * entrada1.setCiudad("Córdoba");
							 * entrada1.setEntradas(150);
							 * listaEntradasCompradas.add(entrada1);
							 */
							if (entrada == null) {
								Thread.sleep(2000);
								System.out.println("> ¡¡ERROR. EVENTO NO EXISTE!!\n");
							} else if (entrada.getEntradas() != 0) {
								Thread.sleep(2000);
								System.out.println(
										"> ¡¡ERROR. NO QUEDAN SUFICIENTES ENTRADAS DISPONIBLES. Entradas disponibles actualmente: "
												+ entrada.getEntradas() + "\n");
							} else {
								Thread.sleep(2000);
								System.out.println("> ¡¡ENTRADA COMPRADA CON ÉXITO!!\n");
							}

							System.out.print("> Pulse enter para continuar\n> ");
							if (ent.hasNextLine()) {
								salto = ent.nextLine();
								break;
							}

						}
						case 2: {

							System.out.println("========================");
							System.out.println("| CANCELAR UNA ENTRADA |");
							System.out.println("========================\n\n");

							System.out.print("> Introduzca el identificador de la compra de la entrada\n> "); // ¿De
																												// donde
																												// se
																												// obtiene
																												// el id
																												// de la
																												// compra.
																												// No
																												// sería
																												// mejor
																												// introducir
																												// el id
																												// del
																												// evento
																												// y el
																												// dni
																												// del
																												// comprador?
							if (ent.hasNextLine())
								idCompra = Integer.parseInt(ent.nextLine());
							System.out.print("\n");

							if ((srv.cancelarEntrada(idCompra)) == true) {
								Thread.sleep(2000);
								System.out.println("> ¡¡ENTRADA ANULADA CON ÉXITO!!\n");
							} else {
								Thread.sleep(2000);
								System.out.println(
										"> FALLO ANULANDO LA ENTRADA. POR FAVOR, INTENTELO DE NUEVO MÁS TARDE...\n");
							}

							System.out.print("> Pulse enter para continuar\n> ");
							if (ent.hasNextLine()) {
								salto = ent.nextLine();
								break;
							}

							/*
							 * System.out.print("> Introduzca el nombre del evento que desea cancelar\n>");
							 * if(ent.hasNextLine())
							 * idevento = ent.nextLine();
							 * System.out.print("\n");
							 */
						}
						case 3: {

							System.out.println("==============================");
							System.out.println("| LISTAR EVENTOS DISPONIBLES |");
							System.out.println("==============================\n\n");

							System.out.println("> Se muestran todos los eventos actualmente disponibles");
							listaEventosDisponibles = srvEvent.listarEventos();

							Iterator i = listaEventosDisponibles.iterator();
							System.out.println("--------------------------------------------");
							while (i.hasNext()) {
								Evento event = (Evento) i.next();
								System.out.println("--------------------------------------------");
								System.out.println("> ID DEL EVENTO: " + event.getId());
								System.out.println("> NOMBRE DEL ARTISTA: " + event.getArtista());
								System.out.println("> FECHA DEL EVENTO: " + event.getFecha());
								System.out.println("> CIUDAD DONDE SE CELEBRA: " + event.getCiudad());
								System.out.println("> LUGAR DEL EVENTO: " + event.getLugar());
								System.out.println("> NUMERO DE ENTRADAS DISPONIBLES: " + event.getEntradas());
								System.out.println("--------------------------------------------");
							}
							System.out.println("--------------------------------------------");
							System.out.print("\n");

							System.out.print("> Pulse enter para continuar\n> ");
							if (ent.hasNextLine()) {
								salto = ent.nextLine();
								break;
							}

						}
						case 4: {

							System.out.println("=================================");
							System.out.println("| LISTAR MIS ENTRADAS COMPRADAS |");
							System.out.println("=================================\n\n");

							System.out.println("> Se muestran todas sus entradas compradas");
							listaEntradasCompradas = srv.listarEntradas(DNI);

							if ((listaEntradasCompradas.isEmpty()) == true) {
								System.out.println("> NO HAY NINGUNA ENTRADA COMPRADA");
							} else {
								Iterator i = listaEntradasCompradas.iterator();
								while (i.hasNext()) {
									Entrada entra = (Entrada) i.next();
									System.out.println("--------------------------------------------");
									System.out.println("> ID DE LA COMPRA DE LA ENTRADA: " + entra.getId());
									System.out.println("> NOMBRE DEL ARTISTA: " + entra.getArtista());
									System.out.println("> CANTIDAD DE ENTRADAS COMPRADAS: " + entra.getEntradas());
									System.out.println("> GRADA: " + entra.getGrada());
									System.out.println("--------------------------------------------");
								}
							}
							System.out.print("\n");

							System.out.print("> Pulse enter para continuar\n> ");
							if (ent.hasNextLine()) {
								salto = ent.nextLine();
								break;
							}

						}
						/*
						 * case 5: {
						 * 
						 * System.out.println("===========================");
						 * System.out.println("| DETALLES DE UNA ENTRADA |");
						 * System.out.println("===========================\n\n");
						 * 
						 * System.out.print("> Introduza el identificador de la entrada\n>");
						 * if(ent.hasNextLine())
						 * identEntrada = ent.nextLine();
						 * System.out.print("\n");
						 * 
						 * ent = srv.detalleEntrada(DNI, identEntrada); //Se podría incluir un nuevo
						 * método para poder comprobar de forma detalla la información de una entrada en
						 * particular. Habría que introducir tambíen una forma para comprobar si existe
						 * la entrada para dicho usuario o no
						 * 
						 * 
						 * 
						 * System.out.println("---------------------------------------------");
						 * System.out.println("ID DE LA ENTRADA: " + ent.getId());
						 * System.out.println("NOMBRE DEL ARTISTA: " + ent.getArtista());
						 * System.out.println("FECHA DEL EVENTO: " + ent.getFecha());
						 * System.out.println("CIUDAD DONDE SE CELEBRA: " + ent.getCiudad());
						 * System.out.println("LUGAR DEL EVENTO: " + ent.getLugar());
						 * System.out.println("---------------------------------------------\n");
						 * 
						 * System.out.print("> Pulse enter para continuar\n>");
						 * if(ent.hasNextLine()){
						 * salto = ent.nextLine();
						 * break;
						 * }
						 * 
						 * }
						 */

						case 5: {

							System.out.println("============================");
							System.out.println("| SELECCIONAR MIS ASIENTOS |");
							System.out.println("============================\n\n");

							System.out.print("> Indique el identificador de la compra\n> ");

							
							if (ent.hasNextLine()) {
								idCompra = Integer.parseInt(ent.nextLine());
							}

							
							System.out.println("> Seleccione la ubicación donde le gustaría estar");

							System.out.println("> Mostrando escenario...");

							Thread.sleep(1500);

							escenario = srv.obtenerEscenario(idCompra);

							ProcessBuilder pb = new ProcessBuilder();

							if (escenario == 1) {

								File fichero = new File("Grafico1.class");
								ruta = fichero.getAbsolutePath();
								rutaFichero = ruta.substring(0, ruta.length() - 15);// 15 pq es la longitud de
																					// GraficoX.class

								pb.command(Arrays.asList("java", "Grafico1"))
										.directory(new File(rutaFichero));
							} else if (escenario == 2) {

								File fichero = new File("Grafico2.class");
								ruta = fichero.getAbsolutePath();
								rutaFichero = ruta.substring(0, ruta.length() - 15);

								pb.command(Arrays.asList("java", "Grafico2"))
										.directory(new File(rutaFichero));
							} else if (escenario == 3) {
								File fichero = new File("Grafico3.class");
								ruta = fichero.getAbsolutePath();
								rutaFichero = ruta.substring(0, ruta.length() - 15);

								pb.command(Arrays.asList("java", "Grafico3"))
										.directory(new File(rutaFichero));
							} else {
								System.out.println("> ¡¡ERROR AL CAMBIAR EL ASIENTO!!!. INTENTELO DE NUEVO MÁS TARDE...\n");
								break;
							}

							Process proceso = pb.start();
							BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
							String grada;
							grada = br.readLine();
							
							proceso.waitFor();

							boolean cambiarAsiento = srv.cambiarAsiento(Integer.parseInt(grada), idCompra);
							if (cambiarAsiento == false) {
								System.out.println("> ¡¡ERROR AL CAMBIAR EL ASIENTO!!!. INTENTELO DE NUEVO MÁS TARDE...\n");
							}
							else
							System.out.println("> ¡¡ASIENTO CAMBIADO CON ÉXITO!!\n");
							
							
							System.out.print("> Pulse enter para continuar\n> ");
							if (ent.hasNextLine()) {
									salto = ent.nextLine();
									break;
							}	
						}

						case 6: {

							System.out.println("> SALIENDO DE LA VENTA DE ENTRADAS...");
							Thread.sleep(2000);
							return;
							/*
							 * if(srv.cancelarSesión()){
							 * System.out.println("SESIÓN CERRADA CORRECTAMENTE...");
							 */
							// Thread.sleep(2000);
							// return;
							/*
							 * }else{
							 * System.out.println("ERROR AL CERRAR LA SESIÓN...");
							 * }
							 */

						}

						default: {

							System.out.println("> OPCIÓN NO VÁLIDA\n");

							System.out.print("> Pulse enter para continuar\n> ");
							if (ent.hasNextLine()) {
								salto = ent.nextLine();
								break;
							}

						}
					}

				} while (true);

			}

		}

		catch (

		RemoteException e) {
			System.err.println("Error de comucicación: " + e.toString());
		}

		catch (Exception e) {
			System.err.println("Excepción en ClienteEntrada: ");
			e.printStackTrace();
		}

	}

}
