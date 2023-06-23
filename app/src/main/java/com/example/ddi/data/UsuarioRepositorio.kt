package com.example.ddi.data

object UsuarioRepositorio {
    private val usuarios = mutableListOf<Usuario>()

    init {
        usuarios.add(Usuario("A", "a", "a@email",
            cursos =
        mutableListOf(Curso("JavaScript I", Usuario(nickname = "Anónimo"),4.8),
                      Curso("JavaScript II", Usuario(nickname = "Anónimo"),4.9)),
            cursosPublicados =
        mutableListOf(Curso("React I", Usuario(nickname = "A"),3.0)),
            cursosSinPublicar =
        mutableListOf(Curso("React II", Usuario(nickname = "A"),3.5))))
    }

    fun agregar(usuario: Usuario) {
        if (!existeNombreOEmail(usuario.nickname, usuario.email)) {
            usuarios.add(usuario)
        }
    }

    fun existe(nickname: String, password: String): Boolean {
        return (usuarios.any { usuario: Usuario -> usuario.nickname == nickname && usuario.password == password })
    }

    private fun existeNombreOEmail(nickname: String, email: String): Boolean {
        return (usuarios.any { usuario: Usuario -> usuario.nickname == nickname || usuario.email == email })
    }

    fun iniciar(nickname: String, password: String): Usuario {
        var usuarioIniciado = Usuario()
        for (elemento in usuarios) {
            if (elemento.nickname == nickname && elemento.password == password) {
                usuarioIniciado = elemento
            }
        }
        return usuarioIniciado
    }

    fun creador(nickname: String): Usuario {
        var creador = Usuario()
        for (elemento in usuarios) {
            if (elemento.nickname == nickname) {
                creador = elemento
            }
        }
        return creador
    }
}