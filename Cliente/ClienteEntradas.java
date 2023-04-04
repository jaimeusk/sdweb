import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
import java.lang.Thread;

class ClienteEntradas {
    static public void main (String args[]) {
	
		if (args.length!=3) {
	    	System.err.println("Uso: ClienteEntrada hostregistro numPuertoSrvEntradas numPuertoSrvEventos");
			return;
		}

		if (System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());

		try {
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
			List<Evento> listaEventosDisponibles = new ArrayList<Evento>();
			List<Entrada> listaEntradasCompradas = new ArrayList<Entrada>();

			String nombreArtista = "";
			String ciudadEvento = "";
			String recintoEvento = "";
			String fechaEvento = "";

			Entrada entrada = new Entrada();
			Evento evento = new Evento();
			ServicioEntradas srv = (ServicioEntradas) Naming.lookup("//" + args[0] + ":" + args[1] + "/Entrada");
			FabricaServicioEvento fabricaE = (FabricaServicioEvento) Naming.lookup("//" + args[0] + ":" + args[2] + "/Eventos");
			ServicioEventos srvEvent = fabricaE.crearServicioEvento();
			Scanner ent = new Scanner(System.in);
			System.out.println("\n\n==================================");
			System.out.println("| SERVICIO DE VENTAS DE ENTRADAS |");
			System.out.println("==================================");

			System.out.println("\n\n==================================");
			System.out.println("|    ¿ES USTED ADMINISTRADOR?    |");
			System.out.println("==================================");
			
			System.out.print("Introduzca 1 si es admin y 2 si es un usuario\n>");
			
			if (ent.hasNextLine())
				esAdmin = Integer.parseInt(ent.nextLine());

			
			if (esAdmin == 1){
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
					//System.out.println("") //AÑADIR EL MENU 5 DE LISTAR TODAS LAS ENTRADAS
	
					System.out.print("Opción > ");
					if(ent.hasNextLine())
						opcion = Integer.parseInt(ent.nextLine());
					System.out.print("\n");


					switch (opcion)	{
						case 1: {
						
							System.out.println("=======================");
							System.out.println("|   CREAR UN EVENTO   |");
							System.out.println("=======================\n\n");
							
							System.out.print("> ¿Cuál es el nombre del artista?\n>");
							if(ent.hasNextLine())
								nombreArtista = ent.nextLine();
							System.out.print("\n");
							
							System.out.print("> ¿En que ciudad será el evento?\n>");
							if(ent.hasNextLine())
								ciudadEvento = ent.nextLine();
							System.out.print("\n");

							System.out.print("> ¿En que recinto se celebrará el evento?\n>");
							if(ent.hasNextLine())
								recintoEvento = ent.nextLine();
							System.out.print("\n");

							System.out.print("> ¿En que fecha será el evento? (aaaa-mm-dd)\n>");
							if(ent.hasNextLine())
								fechaEvento = ent.nextLine();
							System.out.print("\n");

							System.out.print("> ¿Cuántas entradas habrá disponibles?\n>");
							if(ent.hasNextLine())
								numEntradas = Integer.parseInt(ent.nextLine());
							System.out.print("\n");						
						
							
							boolean resultadoCreaEvento;
							resultadoCreaEvento = srvEvent.crearEvento(nombreArtista, fechaEvento, fechaEvento, ciudadEvento, numEntradas);
							
							Thread.sleep(2000);
							
							if(resultadoCreaEvento == true) {
								System.out.println("¡¡EVENTO CREADO CON ÉXITO!!\n");
							} else {
								System.out.println("¡¡ERROR AL CREAR EVENTO!!\n");
							}
							
							
							System.out.print("> Pulse enter para continuar\n>");
							if(ent.hasNextLine()){
								salto = ent.nextLine();
								break;
							}
						
						}
						/*
						case 2: {
							
							System.out.println("========================");
							System.out.println("| CANCELAR UNA ENTRADA |");
							System.out.println("========================\n\n");
							
							System.out.print("> Introduzca el identificador de la compra de la entrada\n>"); //¿De donde se obtiene el id de la compra. No sería mejor introducir el id del evento y el dni del comprador?
							if(ent.hasNextLine())
								idCompra = Integer.parseInt(ent.nextLine());
							System.out.print("\n");

							
							if((srv.cancelarEntrada(idCompra)) == true)
								System.out.println("¡¡ENTRADA ANULADA CON ÉXITO!!\n");
							else
								System.out.println("FALLO ANULANDO LA ENTRADA. POR FAVOR, INTENTELO DE NUEVO MÁS TARDE...\n");
							

							
							System.out.print("> Pulse enter para continuar\n>");
							if(ent.hasNextLine()){
								salto = ent.nextLine();
								break;
							}
							
							/*
							System.out.print("> Introduzca el nombre del evento que desea cancelar\n>");
							if(ent.hasNextLine())
							idevento = ent.nextLine();
							System.out.print("\n");
							*/		
					/*	}
					case 3: {
					
							System.out.println("==============================");
							System.out.println("| LISTAR EVENTOS DISPONIBLES |");
							System.out.println("==============================\n\n");
							
							System.out.println("> Se muestran todos los eventos actualmente disponibles");
							listaEventosDisponibles = srvEvent.listarEventos();
							
							Iterator i = listaEventosDisponibles.iterator();
							while (i.hasNext()) {
								Evento event = (Evento) i.next();
								System.out.println("--------------------------------------------");
								System.out.println("> ID DEL EVENTO: " + event.getId());
								System.out.println("> NOMBRE DEL ARTISTA: " + event.getArtista());
								System.out.println("> FECHA DEL EVENTO: " + event.getFecha());
								System.out.println("> CIUDAD DONDE SE CELEBRA: " + event.getCiudad());
								System.out.println("> LUGAR DEL EVENTO: " +  event.getLugar());
								System.out.println("> NUMERO DE ENTRADAS DISPONIBLES: " + event.getEntradas());
								System.out.println("--------------------------------------------");
							}
							System.out.print("\n");

							System.out.print("> Pulse enter para continuar\n>");
							if(ent.hasNextLine()){
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
								
								if((listaEntradasCompradas.isEmpty()) == true) {
								System.out.println("NO HAY NINGUNA ENTRADA COMPRADA");
								}
								else {
								Iterator i = listaEntradasCompradas.iterator();
								while (i.hasNext()) {
									Entrada entra =  (Entrada) i.next();
									System.out.println("--------------------------------------------");
									System.out.println("> ID DE LA COMPRA DE LA ENTRADA: " + entra.getId());
									System.out.println("> NOMBRE DEL ARTISTA: " + entra.getArtista());
									System.out.println("> CANTIDAD DE ENTRADAS COMPRADAS: " + entra.getEntradas());
									System.out.println("--------------------------------------------");
								}
								}
								System.out.print("\n");
								
								System.out.print("> Pulse enter para continuar\n>");
								if(ent.hasNextLine()){
								salto = ent.nextLine();
								break;
								}
										
							}
							
							/*case 5: {
					
								System.out.println("===========================");
								System.out.println("| DETALLES DE UNA ENTRADA |");
								System.out.println("===========================\n\n");
								
								System.out.print("> Introduza el identificador de la entrada\n>");
								if(ent.hasNextLine())
								identEntrada = ent.nextLine();
								System.out.print("\n");
								
								ent = srv.detalleEntrada(DNI, identEntrada); //Se podría incluir un nuevo método para poder comprobar de forma detalla la información de una entrada en particular. Habría que introducir tambíen una forma para comprobar si existe la entrada para dicho usuario o no

								
								
								System.out.println("---------------------------------------------");
								System.out.println("ID DE LA ENTRADA: " + ent.getId());
								System.out.println("NOMBRE DEL ARTISTA: " + ent.getArtista());
								System.out.println("FECHA DEL EVENTO: " + ent.getFecha());
								System.out.println("CIUDAD DONDE SE CELEBRA: " + ent.getCiudad());
								System.out.println("LUGAR DEL EVENTO: " + ent.getLugar());
								System.out.println("---------------------------------------------\n");

								System.out.print("> Pulse enter para continuar\n>");
								if(ent.hasNextLine()){
								salto = ent.nextLine();
								break;
								}
										
							}*/
							default: {
								System.out.println("OPCIÓN NO VÁLIDA\n");
								System.out.print("> Pulse enter para continuar\n>");
								
								if(ent.hasNextLine()){
									salto = ent.nextLine();
									break;
								}
							}
					}
					
				} while(true);


			// COMIENZA EL MENU USUARIO
			} else {

				System.out.print("Introduzca su nombre \n>");
				if (ent.hasNextLine())
					nombre = ent.nextLine();
				
				System.out.print("Introduzca su apellido \n>");
				if (ent.hasNextLine())
					apellido = ent.nextLine();
					nombre = nombre + " " + apellido;
				
				System.out.print("Introduza su DNI \n>");
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
				//System.out.println("(5)>   DETALLES DE UNA ENTRADA    ");
				//System.out.println("----------------------------------");
				System.out.println("(5)>           CANCELAR           ");
				System.out.println("----------------------------------");

				System.out.print("Opción > ");
				if(ent.hasNextLine())
					opcion = Integer.parseInt(ent.nextLine());
				System.out.print("\n");
				
				switch (opcion)
					{
					case 1: {
					
					System.out.println("=======================");
					System.out.println("| COMPRAR UNA ENTRADA |");
					System.out.println("=======================\n\n");
					
					System.out.print("> Seleccione el evento al que desea acudir\n>");
					if(ent.hasNextLine())
						idEvento = Integer.parseInt(ent.nextLine());
					System.out.print("\n");
					System.out.print("> Seleccione el número de entradas que desea comprar\n>");
					if(ent.hasNextLine())
						numEntradas = Integer.parseInt(ent.nextLine());
					System.out.print("\n");

					
					entrada = srv.comprarEntrada(idEvento, numEntradas, nombre, DNI); //Pondría para comprarEntrada q el valor devuelto sea un boolean para comprobar si se ha comprado o no. También añadiría un parámetro para el apellido
					/*
					Entrada entrada1 = new Entrada();
					entrada1.setId(1);
					entrada1.setArtista("David Bisbal");
					entrada1.setFecha("14/08/23");
					entrada1.setLugar("Plaza de Toros");
					entrada1.setCiudad("Córdoba");
					entrada1.setEntradas(150);
					listaEntradasCompradas.add(entrada1);
					*/
					if(entrada == null){
						Thread.sleep(2000);
						System.out.println("¡¡ERROR. EVENTO NO EXISTE!!\n");
					}else if(entrada.getEntradas()!= 0){
						Thread.sleep(2000);
						System.out.println("¡¡ERROR. NO QUEDAN SUFICIENTES ENTRADAS DISPONIBLES. Entradas disponibles actualmente: " + entrada.getEntradas() + "\n");
					}else{
						Thread.sleep(2000);
						System.out.println("¡¡ENTRADA COMPRADA CON ÉXITO!!\n");}
					
					System.out.print("> Pulse enter para continuar\n>");
					if(ent.hasNextLine()){
						salto = ent.nextLine();
						break;
					}
					
					}
					case 2: {
					
					System.out.println("========================");
					System.out.println("| CANCELAR UNA ENTRADA |");
					System.out.println("========================\n\n");
					
					System.out.print("> Introduzca el identificador de la compra de la entrada\n>"); //¿De donde se obtiene el id de la compra. No sería mejor introducir el id del evento y el dni del comprador?
					if(ent.hasNextLine())
						idCompra = Integer.parseInt(ent.nextLine());
					System.out.print("\n");

					
					if((srv.cancelarEntrada(idCompra)) == true)
						System.out.println("¡¡ENTRADA ANULADA CON ÉXITO!!\n");
					else
						System.out.println("FALLO ANULANDO LA ENTRADA. POR FAVOR, INTENTELO DE NUEVO MÁS TARDE...\n");
					

					
					System.out.print("> Pulse enter para continuar\n>");
					if(ent.hasNextLine()){
						salto = ent.nextLine();
						break;
					}
					
					/*
					System.out.print("> Introduzca el nombre del evento que desea cancelar\n>");
					if(ent.hasNextLine())
					idevento = ent.nextLine();
					System.out.print("\n");
					*/		
					}
					case 3: {
					
					System.out.println("==============================");
					System.out.println("| LISTAR EVENTOS DISPONIBLES |");
					System.out.println("==============================\n\n");
					
					System.out.println("> Se muestran todos los eventos actualmente disponibles");
					listaEventosDisponibles = srvEvent.listarEventos();
					
					Iterator i = listaEventosDisponibles.iterator();
					while (i.hasNext()) {
						Evento event = (Evento) i.next();
						System.out.println("--------------------------------------------");
						System.out.println("> ID DEL EVENTO: " + event.getId());
						System.out.println("> NOMBRE DEL ARTISTA: " + event.getArtista());
						System.out.println("> FECHA DEL EVENTO: " + event.getFecha());
						System.out.println("> CIUDAD DONDE SE CELEBRA: " + event.getCiudad());
						System.out.println("> LUGAR DEL EVENTO: " +  event.getLugar());
						System.out.println("> NUMERO DE ENTRADAS DISPONIBLES: " + event.getEntradas());
						System.out.println("--------------------------------------------");
					}
					System.out.print("\n");

					System.out.print("> Pulse enter para continuar\n>");
					if(ent.hasNextLine()){
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
						
						if((listaEntradasCompradas.isEmpty()) == true) {
						System.out.println("NO HAY NINGUNA ENTRADA COMPRADA");
						}
						else {
						Iterator i = listaEntradasCompradas.iterator();
						while (i.hasNext()) {
							Entrada entra =  (Entrada) i.next();
							System.out.println("--------------------------------------------");
							System.out.println("> ID DE LA COMPRA DE LA ENTRADA: " + entra.getId());
							System.out.println("> NOMBRE DEL ARTISTA: " + entra.getArtista());
							System.out.println("> CANTIDAD DE ENTRADAS COMPRADAS: " + entra.getEntradas());
							System.out.println("--------------------------------------------");
						}
						}
						System.out.print("\n");
						
						System.out.print("> Pulse enter para continuar\n>");
						if(ent.hasNextLine()){
						salto = ent.nextLine();
						break;
						}
										
					}
						/*case 5: {
					
						System.out.println("===========================");
						System.out.println("| DETALLES DE UNA ENTRADA |");
						System.out.println("===========================\n\n");
						
						System.out.print("> Introduza el identificador de la entrada\n>");
						if(ent.hasNextLine())
						identEntrada = ent.nextLine();
						System.out.print("\n");
						
						ent = srv.detalleEntrada(DNI, identEntrada); //Se podría incluir un nuevo método para poder comprobar de forma detalla la información de una entrada en particular. Habría que introducir tambíen una forma para comprobar si existe la entrada para dicho usuario o no

						
						
						System.out.println("---------------------------------------------");
						System.out.println("ID DE LA ENTRADA: " + ent.getId());
						System.out.println("NOMBRE DEL ARTISTA: " + ent.getArtista());
						System.out.println("FECHA DEL EVENTO: " + ent.getFecha());
						System.out.println("CIUDAD DONDE SE CELEBRA: " + ent.getCiudad());
						System.out.println("LUGAR DEL EVENTO: " + ent.getLugar());
						System.out.println("---------------------------------------------\n");

						System.out.print("> Pulse enter para continuar\n>");
						if(ent.hasNextLine()){
						salto = ent.nextLine();
						break;
						}
										
					}*/
					
						case 5: {
						
						
						System.out.println("SALIENDO DE LA VENTA DE ENTRADAS...");
						Thread.sleep(2000);
						return;
						/*if(srv.cancelarSesión()){
							System.out.println("SESIÓN CERRADA CORRECTAMENTE...");*/
							//Thread.sleep(2000);
							//return;
						/*}else{
							System.out.println("ERROR AL CERRAR LA SESIÓN...");
						}*/
						
						
					}
						default: {
						
						System.out.println("OPCIÓN NO VÁLIDA\n");

						System.out.print("> Pulse enter para continuar\n>");
						if(ent.hasNextLine()){
						salto = ent.nextLine();
						break;
						}
						
					}
					}
					
					
					}while(true);
						

		}

	}

	    
	
	catch (RemoteException e) {
	    System.err.println("Error de comucicación: " + e.toString());
	}
	
	catch (Exception e) {
	    System.err.println("Excepción en ClienteEntrada: ");
	    e.printStackTrace();
	}
	
    }
    
}
	
	
	
