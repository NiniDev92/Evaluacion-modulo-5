package com.talento.evaluacinmdulo5.model

class LocalProductRepository {

    fun returnShoeList(): List<ShoeItem> {
        return listOf(
            ShoeItem(
                id = "shoe_01",
                nombre = "Air Glide Neo",
                urlImagen = "https://images.unsplash.com/photo-1549298916-b41d501d3772?auto=format&fit=crop&w=1200&q=80",
                precio = 99_990.0
            ),
            ShoeItem(
                id = "shoe_02",
                nombre = "Urban Flex Runner",
                urlImagen = "https://images.unsplash.com/photo-1512374382149-233c42b6a83b?auto=format&fit=crop&w=1200&q=80",
                precio = 79_990.0
            ),
            ShoeItem(
                id = "shoe_03",
                nombre = "Classic Leather Street",
                urlImagen = "https://images.unsplash.com/photo-1525966222134-fcfa99b8ae77?auto=format&fit=crop&w=1200&q=80",
                precio = 109_990.0
            ),
            ShoeItem(
                id = "shoe_04",
                nombre = "Summit Trail Pro",
                urlImagen = "https://images.unsplash.com/photo-1608231387042-66d1773070a5?auto=format&fit=crop&w=1200&q=80",
                precio = 129_990.0
            ),
            ShoeItem(
                id = "shoe_05",
                nombre = "Velocity Knit",
                urlImagen = "https://images.unsplash.com/photo-1465453869711-7e174808ace9?auto=format&fit=crop&w=1200&q=80",
                precio = 84_990.0
            ),
            ShoeItem(
                id = "shoe_06",
                nombre = "Retro Court Low",
                urlImagen = "https://images.unsplash.com/photo-1560769629-975ec94e6a86?auto=format&fit=crop&w=1200&q=80",
                precio = 89_990.0
            ),
            ShoeItem(
                id = "shoe_07",
                nombre = "Storm Grip X",
                urlImagen = "https://images.unsplash.com/photo-1607522370275-f14206abe5d3?auto=format&fit=crop&w=1200&q=80",
                precio = 139_990.0
            ),
            ShoeItem(
                id = "shoe_08",
                nombre = "Nimbus Foam",
                urlImagen = "https://images.unsplash.com/photo-1595950653106-6c9ebd614d3a?auto=format&fit=crop&w=1200&q=80",
                precio = 94_990.0
            ),
            ShoeItem(
                id = "shoe_09",
                nombre = "Canvas Chill",
                urlImagen = "https://images.unsplash.com/photo-1552346154-21d32810aba3?auto=format&fit=crop&w=1200&q=80",
                precio = 64_990.0
            ),
            ShoeItem(
                id = "shoe_10",
                nombre = "Prime Sprint",
                urlImagen = "https://images.unsplash.com/photo-1533867617858-e7b97e060509?auto=format&fit=crop&w=1200&q=80",
                precio = 149_990.0
            ),
            ShoeItem(
                id = "shoe_11",
                nombre = "City Walk Comfort",
                urlImagen = "https://images.unsplash.com/photo-1614252235316-8c857d38b5f4?auto=format&fit=crop&w=1200&q=80",
                precio = 72_990.0
            ),
            ShoeItem(
                id = "shoe_12",
                nombre = "Terra Hike Mid",
                urlImagen = "https://images.unsplash.com/photo-1529810313688-44ea1c2d81d3?auto=format&fit=crop&w=1200&q=80",
                precio = 159_990.0
            )
        )
    }
}
