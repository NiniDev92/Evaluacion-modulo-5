Shoes Tap - Evaluacion Final Modulo 5
Aplicacion Android en Kotlin 
•
Funcional y compilando sin errores.
•
Arquitectura MVP aplicada en las 3 pantallas principales.
•
APK generado y copiado en:
◦
build/ShoesTap-debug.apk
Funcionalidades implementadas
1. Pantalla de inicio (Fragment + RecyclerView)
•
ProductListFragment con RecyclerView.
•
Lista local hardcoded con 12 productos (LocalProductRepository).
•
Cada item muestra:
◦
Imagen por URL (con Glide)
◦
Nombre
◦
Precio formateado en CLP
•
Navegacion al detalle al tocar un producto.
2. Pantalla de detalle del producto
•
ProductDetailFragment.
•
Muestra:
◦
Nombre
◦
Imagen
◦
Precio
◦
Descripcion
◦
Boton Agregar al carrito
•
Navegacion directa al carrito.
•
Paso de datos entre pantallas por Bundle.
3. Carrito de compras
•
CartFragment con RecyclerView.
•
Muestra productos agregados y total acumulado.
•
Acciones:
◦
Eliminar producto individual
◦
Vaciar carrito completo
◦
Seguir comprando
•
Persistencia local con SharedPreferences (CartStorage).
Arquitectura MVP
Model
•
ShoeItem
•
LocalProductRepository
•
CartStorage
View
•
ProductListFragment
•
ProductDetailFragment
•
CartFragment
•
Layouts XML + ViewBinding
Presenter
•
ProductListPresenter
•
ProductDetailPresenter
•
CartPresenter
•
Contratos:
◦
ProductListContract
◦
ProductDetailContract
◦
CartContract
Estructura del proyecto
app/src/main/java/com/talento/evaluacinmdulo5
|-- MainActivity.kt
|-- model/
|-- presenter/
|   |-- contract/
|-- ui/
|   |-- adapter/
|   |-- navigation/
Dependencias usadas
•
androidx.recyclerview:recyclerview
•
androidx.fragment:fragment-ktx
•
com.github.bumptech.glide:glide
•
Material Components
Compilacion local
./gradlew.bat assembleDebug
