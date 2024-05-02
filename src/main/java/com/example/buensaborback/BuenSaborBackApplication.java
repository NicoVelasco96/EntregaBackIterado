package com.example.buensaborback;

import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.domain.entities.enums.Estado;
import com.example.buensaborback.domain.entities.enums.FormaPago;
import com.example.buensaborback.domain.entities.enums.TipoEnvio;
import com.example.buensaborback.domain.entities.enums.TipoPromocion;
import com.example.buensaborback.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
public class BuenSaborBackApplication {

	private static final Logger logger = LoggerFactory.getLogger(BuenSaborBackApplication.class);

	@Autowired
	private FacturaRepository facturaRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PaisRepository paisRepository;

	@Autowired
	private ProvinciaRepository provinciaRepository;

	@Autowired
	private LocalidadRepository localidadRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private SucursalRepository	sucursalRepository;

	@Autowired
	private DomicilioRepository domicilioRepository;

	@Autowired
	private UnidadMedidaRepository unidadMedidaRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ArticuloInsumoRepository articuloInsumoRepository;

	@Autowired
	private ArticuloManufacturadoRepository articuloManufacturadoRepository;

	@Autowired
	private ImagenRepository imagenRepository;

	@Autowired
	private PromocionRepository promocionRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;



	public static void main(String[] args) {
		SpringApplication.run(BuenSaborBackApplication.class, args);
		logger.info("Estoy activo en el main");
	}


	@Bean
	CommandLineRunner init() {
		return args -> {
			logger.info("----------------ESTOY----FUNCIONANDO---------------------");
			////////////////////////////////////////////////////////
			Pais paisArgentina = Pais.builder().nombre("Argentina").build();
			Provincia provinciaMendoza = Provincia.builder().nombre("Mendoza").pais(paisArgentina).build();
			Localidad localidadLasHeras = Localidad.builder().nombre("Las Heras").provincia(provinciaMendoza).build();

			paisRepository.save(paisArgentina);
			provinciaRepository.save(provinciaMendoza);
			localidadRepository.save(localidadLasHeras);

			Empresa empresaMcDonalds = Empresa.builder().nombre("Mc Donalds").cuil(4286585).razonSocial("Venta de Comida Rapida").build();
			Sucursal sucursalLasHeras = Sucursal.builder().nombre("Sucursal Las Heras").horarioApertura(LocalTime.of(13,0)).horarioCierre(LocalTime.of(23,30)).build();
			Domicilio domicilioSucursal = Domicilio.builder().cp(5539).calle("Olascoaga").numero(935).localidad(localidadLasHeras).build();

			sucursalLasHeras.setDomicilio(domicilioSucursal);
			sucursalLasHeras.setEmpresa(empresaMcDonalds);
			empresaMcDonalds.getSucursales().add(sucursalLasHeras);
			domicilioRepository.save(domicilioSucursal);
			empresaRepository.save(empresaMcDonalds);
			sucursalRepository.save(sucursalLasHeras);


			//Unidades de medicion
			UnidadMedida unidadMedidaLitros = UnidadMedida.builder().denominacion("Litros").build();
			UnidadMedida unidadMedidaGramos = UnidadMedida.builder().denominacion("Gramos").build();
			UnidadMedida unidadMedidaCantidad = UnidadMedida.builder().denominacion("Cantidad").build();
			UnidadMedida unidadMedidaPorciones = UnidadMedida.builder().denominacion("Porciones").build();

			//Persistir las unidades
			unidadMedidaRepository.save(unidadMedidaLitros);
			unidadMedidaRepository.save(unidadMedidaGramos);
			unidadMedidaRepository.save(unidadMedidaCantidad);
			unidadMedidaRepository.save(unidadMedidaPorciones);


			//Categorias
			Categoria categoriaBebidas = Categoria.builder().denominacion("Bebidas").build();
			categoriaRepository.save(categoriaBebidas);
			Categoria categoriaGaseosas = Categoria.builder().denominacion("Gaseosas").build();
			categoriaGaseosas.setCategoria(categoriaBebidas);
			categoriaRepository.save(categoriaGaseosas);
			Categoria categoriaGuarniciones = Categoria.builder().denominacion("Guarniciones").build();
			categoriaRepository.save(categoriaGuarniciones);
			Categoria categoriaHamburguesas = Categoria.builder().denominacion("Hamburguesas").build();
			categoriaRepository.save(categoriaHamburguesas);
			Categoria categoriaInsumos = Categoria.builder().denominacion("Insumos").build();
			categoriaRepository.save(categoriaInsumos);
			categoriaBebidas.getSubCategorias().add(categoriaGaseosas);
			categoriaRepository.save(categoriaBebidas);

			sucursalLasHeras.getCategorias().add(categoriaBebidas);
			sucursalLasHeras.getCategorias().add(categoriaGuarniciones);
			sucursalLasHeras.getCategorias().add(categoriaHamburguesas);

			//Crear Insumos
			ArticuloInsumo cocaCola = ArticuloInsumo.builder().denominacion("Coca cola").unidadMedida(unidadMedidaLitros).esParaElaborar(false).stockActual(5).stockMaximo(50).precioCompra(50.0).precioVenta(70.0).build();
			ArticuloInsumo panHamburguesa = ArticuloInsumo.builder().denominacion("Pan para Hamburguesas").unidadMedida(unidadMedidaCantidad).esParaElaborar(false).stockActual(4).stockMaximo(40).precioCompra(40.0).precioVenta(60.5).build();
			ArticuloInsumo queso = ArticuloInsumo.builder().denominacion("Queso").unidadMedida(unidadMedidaGramos).esParaElaborar(true).stockActual(20).stockMaximo(50).precioCompra(23.6).precioVenta(66.6).build();
			ArticuloInsumo tomate = ArticuloInsumo.builder().denominacion("Tomate").unidadMedida(unidadMedidaCantidad).esParaElaborar(true).stockActual(20).stockMaximo(50).precioCompra(23.6).precioVenta(66.6).build();
			ArticuloInsumo carne = ArticuloInsumo.builder().denominacion("Medallones de Carne"). unidadMedida(unidadMedidaCantidad).esParaElaborar(true).stockActual(100).stockMaximo(250).precioCompra(12.50).precioVenta(45.90).build();
			ArticuloInsumo lechuga = ArticuloInsumo.builder().denominacion("Lechuga").unidadMedida(unidadMedidaCantidad).esParaElaborar(true).stockActual(30).stockMaximo(100).precioCompra(6.20).precioVenta(12.20).build();
			ArticuloInsumo papasBaston = ArticuloInsumo.builder().denominacion("Papas cortadas en baston").unidadMedida(unidadMedidaGramos).esParaElaborar(true).stockActual(20000).stockMaximo(30000).precioVenta(4.50).precioCompra(2.50).build();

			//Crear Imagenes de Insumo
			Imagen imagenCoca = Imagen.builder().url("https://m.media-amazon.com/images/I/51v8nyxSOYL._SL1500_.jpg").articulo(cocaCola).build();
			Imagen imagenQueso = Imagen.builder().url("https://superdepaso.com.ar/wp-content/uploads/2021/06/SANTAROSA-PATEGRAS-04.jpg").articulo(queso).build();
			Imagen imagenTomate = Imagen.builder().url("https://thefoodtech.com/wp-content/uploads/2020/06/Componentes-de-calidad-en-el-tomate-828x548.jpg").articulo(tomate).build();
			Imagen imagenCarne = Imagen.builder().url("https://acdn.mitiendanube.com/stores/002/255/209/products/360_f_268569020_l9w6h8hpatmfiqaujo6rdj2emw9dayim1-a8adb76639479aceb416802701257037-640-0.jpg").articulo(carne).build();
			Imagen imagenPanHamburguesa = Imagen.builder().url("https://w7.pngwing.com/pngs/795/295/png-transparent-bun-hamburger-mcdonald-s-big-mac-guacamole-apple-pie-bun-baked-goods-food-american-food.png").articulo(panHamburguesa).build();
			Imagen imagenLechuga = Imagen.builder().url("https://static.vecteezy.com/system/resources/previews/005/582/269/non_2x/lettuce-salad-leaf-isolated-on-white-background-with-clipping-path-free-photo.jpg").articulo(lechuga).build();
			Imagen imagenPapas = Imagen.builder().url("https://biomac.com.ar/wp-content/uploads/2020/02/Papa-Baston.jpg").articulo(papasBaston).build();

			//Guardar las imagenes en los insumos
			cocaCola.getImagenes().add(imagenCoca);
			queso.getImagenes().add(imagenQueso);
			tomate.getImagenes().add(imagenTomate);
			carne.getImagenes().add(imagenCarne);
			panHamburguesa.getImagenes().add(imagenPanHamburguesa);
			lechuga.getImagenes().add(imagenLechuga);
			papasBaston.getImagenes().add(imagenPapas);

			//guardamos las categorias en los insumos
			cocaCola.setCategoria(categoriaGaseosas);
			queso.setCategoria(categoriaInsumos);
			tomate.setCategoria(categoriaInsumos);
			carne.setCategoria(categoriaInsumos);
			panHamburguesa.setCategoria(categoriaInsumos);
			lechuga.setCategoria(categoriaInsumos);
			papasBaston.setCategoria(categoriaInsumos);

			//Persistir los insumos
			articuloInsumoRepository.save(cocaCola);
			articuloInsumoRepository.save(queso);
			articuloInsumoRepository.save(tomate);
			articuloInsumoRepository.save(carne);
			articuloInsumoRepository.save(panHamburguesa);
			articuloInsumoRepository.save(lechuga);
			articuloInsumoRepository.save(papasBaston);

			//Crear los insumos manufacturados
			ArticuloManufacturado hamburSimple = ArticuloManufacturado.builder().denominacion("Hamburguesa Simple").descripcion("Hamburguesa sin verduras").unidadMedida(unidadMedidaCantidad).precioVenta(150.50).tiempoEstimadoMinutos(30).preparacion("Lee la receta...").build();
			ArticuloManufacturado hamburComple = ArticuloManufacturado.builder().denominacion("Hamburguesa Completa").descripcion("Hamburguesa con verduras").unidadMedida(unidadMedidaCantidad).precioVenta(180d).tiempoEstimadoMinutos(35).preparacion("Lee la receta...").build();
			ArticuloManufacturado papasSML= ArticuloManufacturado.builder().denominacion("Papas Fritas Chicas").descripcion("Porcion Papas chicas").unidadMedida(unidadMedidaPorciones).precioVenta(60d).tiempoEstimadoMinutos(10).preparacion("Tiralas a la freidora y sacalas cuando esten doraditas").build();
			ArticuloManufacturado papasXL = ArticuloManufacturado.builder().denominacion("Papas Fritas Grandes").descripcion("Porcion Papas grandes").unidadMedida(unidadMedidaPorciones).precioVenta(90d).tiempoEstimadoMinutos(20).preparacion("Tiralas a la freidora y sacalas cuando esten doraditas").build();

			//Crear las imagenes para los articulos manufacturados
			Imagen imagenHambS = Imagen.builder().url("https://cache-backend-mcd.mcdonaldscupones.com/media/image/product$kqX8TYcp/200/200/original?country=ar").articulo(hamburSimple).build();
			Imagen imagenHambCom = Imagen.builder().url("https://www.hogar.mapfre.es/media/2018/09/hamburguesa-sencilla.jpg").articulo(hamburComple).build();
			Imagen imagenPapasSML= Imagen.builder().url("https://www.durum.com.ar/wp-content/uploads/2019/10/Papas-Fritas-Caseras-Grande-e1570837578622.jpg").articulo(papasSML).build();
			Imagen imagenPapasXL = Imagen.builder().url("https://laclass.cl/wp-content/uploads/2020/11/PapasGrandes.jpg").articulo(papasXL).build();

			//Guardar las imagenes en los articulos manufacturados
			hamburSimple.getImagenes().add(imagenHambS);
			hamburComple.getImagenes().add(imagenHambCom);
			papasSML.getImagenes().add(imagenPapasSML);
			papasXL.getImagenes().add(imagenPapasXL);

			//Guardamos las categorias en los articulos manufacturados
			hamburSimple.setCategoria(categoriaHamburguesas);
			hamburComple.setCategoria(categoriaHamburguesas);
			papasSML.setCategoria(categoriaGuarniciones);
			papasXL.setCategoria(categoriaGuarniciones);

			//Guardamos los articulos manufacturados
			articuloManufacturadoRepository.save(hamburSimple);
			articuloManufacturadoRepository.save(hamburComple);
			articuloManufacturadoRepository.save(papasSML);
			articuloManufacturadoRepository.save(papasXL);

			//Crear los detalles para cada articulo manufacturado
			ArticuloManufacturadoDetalle detallePanHamburSimple= ArticuloManufacturadoDetalle.builder().cantidad(1d).articuloInsumo(panHamburguesa).articuloManufacturado(hamburSimple).build();
			ArticuloManufacturadoDetalle detalleQuesoHamburSimple =ArticuloManufacturadoDetalle.builder().cantidad(1d).articuloInsumo(queso).articuloManufacturado(hamburSimple).build();
			ArticuloManufacturadoDetalle detalleCarneHamburSimple = ArticuloManufacturadoDetalle.builder().cantidad(1d).articuloInsumo(carne).articuloManufacturado(hamburSimple).build();

			ArticuloManufacturadoDetalle detallePanHamburCompl = ArticuloManufacturadoDetalle.builder().cantidad(1d).articuloInsumo(panHamburguesa).articuloManufacturado(hamburComple).build();
			ArticuloManufacturadoDetalle detalleQuesoHamburCompl = ArticuloManufacturadoDetalle.builder().cantidad(1d).articuloInsumo(queso).articuloManufacturado(hamburComple).build();
			ArticuloManufacturadoDetalle detalleLechugaHamburCompl = ArticuloManufacturadoDetalle.builder().cantidad(2d).articuloInsumo(lechuga).articuloManufacturado(hamburComple).build();
			ArticuloManufacturadoDetalle detalleTomateHamburCompl= ArticuloManufacturadoDetalle.builder().cantidad(2d).articuloInsumo(tomate).articuloManufacturado(hamburComple).build();
			ArticuloManufacturadoDetalle detalleCarneHamburCompl = ArticuloManufacturadoDetalle.builder().cantidad(1d).articuloInsumo(carne).articuloManufacturado(hamburComple).build();

			ArticuloManufacturadoDetalle detallePapasSML = ArticuloManufacturadoDetalle.builder().cantidad(250d).articuloInsumo(papasBaston).articuloManufacturado(papasSML).build();
			ArticuloManufacturadoDetalle detallePapasXL = ArticuloManufacturadoDetalle.builder().cantidad(750d).articuloInsumo(papasBaston).articuloManufacturado(papasXL).build();

			//Guardamos los detalles a los articulos manufacturados
			hamburSimple.getArticuloManufacturadoDetalles().add(detallePanHamburSimple);
			hamburSimple.getArticuloManufacturadoDetalles().add(detalleQuesoHamburSimple);
			hamburSimple.getArticuloManufacturadoDetalles().add(detalleCarneHamburSimple);

			hamburComple.getArticuloManufacturadoDetalles().add(detallePanHamburCompl);
			hamburComple.getArticuloManufacturadoDetalles().add(detalleQuesoHamburCompl);
			hamburComple.getArticuloManufacturadoDetalles().add(detalleCarneHamburCompl);
			hamburComple.getArticuloManufacturadoDetalles().add(detalleLechugaHamburCompl);
			hamburComple.getArticuloManufacturadoDetalles().add(detalleTomateHamburCompl);

			papasSML.getArticuloManufacturadoDetalles().add(detallePapasSML);
			papasXL.getArticuloManufacturadoDetalles().add(detallePapasXL);

			//Actualizamos los insumos manufacturados
			articuloManufacturadoRepository.save(hamburSimple);
			articuloManufacturadoRepository.save(hamburComple);
			articuloManufacturadoRepository.save(papasSML);
			articuloManufacturadoRepository.save(papasXL);

			Promocion promocionHamburguesaParaCompartir = Promocion.builder().denominacion("Promo Burguer Simple para 2")
					.fechaDesde(LocalDate.of(2024,1,1))
					.fechaHasta(LocalDate.of(2024,6,30))
					.horaDesde(LocalTime.of(0,0))
					.horaHasta(LocalTime.of(23,59))
					.descripcionDescuento("Dos hamburguesas simples, 1 porcion de papas SML y 1 Coca Cola")
					.precioPromocional(10000.00)
					.tipoPromocion(TipoPromocion.Promocion)
					.build();

			promocionHamburguesaParaCompartir.getArticulos().add(hamburSimple);
			promocionHamburguesaParaCompartir.getArticulos().add(papasSML);
			promocionHamburguesaParaCompartir.getArticulos().add(cocaCola);
			promocionHamburguesaParaCompartir.getSucursales().add(sucursalLasHeras);
			promocionRepository.save(promocionHamburguesaParaCompartir);

			sucursalLasHeras.getPromociones().add(promocionHamburguesaParaCompartir);
			sucursalLasHeras.getCategorias().add(categoriaGaseosas);
			sucursalLasHeras.getCategorias().add(categoriaHamburguesas);
			sucursalRepository.save(sucursalLasHeras);

			//Crear cliente
			Imagen imagenCliente = Imagen.builder().url("https://hips.hearstapps.com/hmg-prod/images/la-la-land-final-1638446140.jpg").promocion(promocionHamburguesaParaCompartir).build();
			Usuario usuario = Usuario.builder().username("sebastian").auth0Id("9565a49d-ecc1-4f4e-adea-6cdcb7edc4a3").build();
			usuarioRepository.save(usuario);
			Domicilio clienteDomicilio = Domicilio.builder().cp(5539).calle("Martin Fierro").numero(253).localidad(localidadLasHeras).build();
			domicilioRepository.save(clienteDomicilio);
			Cliente cliente = Cliente.builder().usuario(usuario)
					.imagen(imagenCliente)
					.email("correoFalso@gmail.com")
					.nombre("Sebastian")
					.apellido("Wilder")
					.telefono("2615920825")
					.fechaNacimiento(LocalDate.of(1990,5,8))
					.build();
			cliente.getDomicilios().add(clienteDomicilio);
			cliente.setImagen(imagenCliente);
			clienteRepository.save(cliente);

			//Crea un pedido para el cliente
			Pedido pedido = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(300.0)
					.totalCosto(170.6)
					.estado(Estado.Preparacion)
					.formaPago(FormaPago.MercadoPago)
					.tipoEnvio(TipoEnvio.Delivery)
					.sucursal(sucursalLasHeras)
					.domicilio(clienteDomicilio)
					.cliente(cliente)
					.build();

			DetallePedido detallePedido1 = DetallePedido.builder().articulo(hamburComple).cantidad(2).subTotal(200.0).pedido(pedido).build();
			DetallePedido detallePedido2 = DetallePedido.builder().articulo(cocaCola).cantidad(1).subTotal(100.0).pedido(pedido).build();
			pedido.getDetallePedidos().add(detallePedido1);
			pedido.getDetallePedidos().add(detallePedido2);
			pedidoRepository.save(pedido);

			Factura factura = Factura.builder().fechaFacturacion(LocalDate.now())
					.mpPaymentId(1)
					.mpMerchantOrderId(2)
					.mpPreferenceId("3")
					.mpPaymentType("4")
					.formaPago(FormaPago.MercadoPago)
					.totalVenta(300.0)
					.pedido(pedido)
					.build();
			pedido.setFactura(factura);
			cliente.getPedidos().add(pedido);
			facturaRepository.save(factura);
		};
	}
}







