package com.duoc.notariapp


import com.duoc.notariapp.model.TramiteApi
import com.duoc.notariapp.model.UsuarioApi
import com.duoc.notariapp.remote.ApiService
import com.duoc.notariapp.remote.UsuarioApiService
import com.duoc.notariapp.repository.TramiteRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class NotariaUnitTest {

    private class FakeApiService : ApiService {
        private val listaTramites = mutableListOf(
            TramiteApi(
                id = 1,
                nombre = "Poder Especial",
                descripcion = "Autorización notarial para trámites bancarios",
                precio = 15000.0
            )
        )

        override suspend fun obtenerTramites(): List<TramiteApi> = listaTramites

        override suspend fun crearTramite(tramite: TramiteApi): TramiteApi {
            val nuevo = tramite.copy(id = listaTramites.size + 1)
            listaTramites.add(nuevo)
            return nuevo
        }

        override suspend fun obtenerTramitePorId(id: Int): TramiteApi =
            listaTramites.first { it.id == id }

        override suspend fun actualizarTramite(id: Int, tramite: TramiteApi): TramiteApi = tramite
        override suspend fun editarTramite(id: Int, tramite: TramiteApi): TramiteApi = tramite

        override suspend fun eliminarTramite(id: Int) {
            listaTramites.removeAll { it.id == id }
        }
    }

    private class FakeUsuarioApiService : UsuarioApiService {
        private val listaUsuarios = mutableListOf<UsuarioApi>()

        override suspend fun crearUsuario(usuario: UsuarioApi): UsuarioApi {
            val nuevo = usuario.copy(id = listaUsuarios.size + 1)
            listaUsuarios.add(nuevo)
            return nuevo
        }

        override suspend fun obtenerUsuarios(): List<UsuarioApi> = listaUsuarios
        override suspend fun editarUsuario(id: Int, usuario: UsuarioApi): UsuarioApi = usuario

        override suspend fun obtenerUsuarioPorId(id: Int): UsuarioApi =
            listaUsuarios.first { it.id == id }

        override suspend fun eliminarUsuario(id: Int) {
            listaUsuarios.removeAll { it.id == id }
        }

        override suspend fun actualizarUsuario(id: Int, usuario: UsuarioApi): UsuarioApi = usuario
    }

    @Test
    fun `crear y obtener tramites mediante repositorio funciona correctamente`() = runTest {
        println("=== INICIANDO PRUEBA 1: POST y GET de Trámites ===")
        val repository = TramiteRepository(FakeApiService(), FakeUsuarioApiService())

        val tramiteCreado = repository.crearTramite(
            TramiteApi(
                nombre = "Declaración Jurada",
                descripcion = "Certificación de domicilio",
                precio = 8000.0
            )
        )
        println("Trámite creado (POST) -> ID: ${tramiteCreado.id}, Nombre: ${tramiteCreado.nombre}, Precio: $${tramiteCreado.precio}")

        val tramites = repository.obtenerTramites()
        println("Total de trámites obtenidos (GET): ${tramites.size}")
        tramites.forEach { println(" - [${it.id}] ${it.nombre}: $${it.precio}") }

        assertEquals(2, tramiteCreado.id)
        assertEquals(2, tramites.size)
        assertEquals("Declaración Jurada", tramites.last().nombre)
        assertEquals(8000.0, tramites.last().precio, 0.01)
        println("=== PRUEBA 1 APROBADA ===\n")
    }

    @Test
    fun `registrar y obtener usuarios mediante repositorio retorna datos validos`() = runTest {
        println("=== INICIANDO PRUEBA 2: POST y GET de Usuarios ===")
        val repository = TramiteRepository(FakeApiService(), FakeUsuarioApiService())

        val usuarioCreado = repository.crearUsuario(
            UsuarioApi(
                nombre = "Camila Soto",
                rut = "18.456.789-2",
                correo = "camila.soto@duocuc.cl",
                telefono = "+56987654321"
            )
        )
        println("Usuario registrado (POST) -> ID: ${usuarioCreado.id}, Nombre: ${usuarioCreado.nombre}, RUT: ${usuarioCreado.rut}")

        val usuarios = repository.obtenerUsuarios()
        println("Total de usuarios obtenidos (GET): ${usuarios.size} | Correo: ${usuarios.first().correo}")

        assertFalse(usuarios.isEmpty())
        assertEquals("Camila Soto", usuarios.first().nombre)
        assertTrue(usuarios.first().correo.contains("@"))
        println("=== PRUEBA 2 APROBADA ===\n")
    }

    @Test
    fun `eliminar tramite por id mediante repositorio remueve el registro correctamente`() = runTest {
        println("=== INICIANDO PRUEBA 3: DELETE de Trámite ===")
        val repository = TramiteRepository(FakeApiService(), FakeUsuarioApiService())

        val cantidadInicial = repository.obtenerTramites().size
        println("Cantidad inicial de trámites: $cantidadInicial")

        repository.eliminarTramite(1)
        println("Ejecutando DELETE para trámite con ID = 1...")

        val tramitesRestantes = repository.obtenerTramites()
        println("Cantidad de trámites después de eliminar: ${tramitesRestantes.size}")

        assertTrue(tramitesRestantes.isEmpty())
        println("=== PRUEBA 3 APROBADA ===\n")
    }
}