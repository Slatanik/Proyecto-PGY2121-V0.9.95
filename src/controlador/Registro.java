/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import bd.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Libro;
import modelo.Usuario;

/**
 *
 * @author administrador
 */
public class Registro {

    public boolean agregar(Libro libro) {
        Date date;
        try {
            Conexion conexion1 = new Conexion();
            Connection cnx = conexion1.obtenerConexion();

            date = libro.getPublicacion();
            
            String query = "INSERT INTO libro(titulo, autor, publicacion, precio, disponible, descripcion) VALUES (?,?,?,?,?,?)";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setDate(3, new java.sql.Date(date.getTime()));
            stmt.setInt(4, libro.getPrecio());
            stmt.setBoolean(5, libro.isDisponible());
            stmt.setString(6, libro.getDescripcion());

            stmt.executeUpdate();
            stmt.close();
            cnx.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error SQL al agregar paciente" + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error al agregar paciente" + e.getMessage());
            return false;
        }
    }

     public boolean eliminar(int idLibro) {
        try {
            boolean resultado = false;
            Conexion conexion1 = new Conexion();
            Connection cnx = conexion1.obtenerConexion();

            String query = "DELETE FROM libro WHERE idlibro=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setInt(1, idLibro);

            int rs = stmt.executeUpdate();
            stmt.close();
            cnx.close();
            if(rs > 0){
                resultado = true;
            }
            return resultado;
        } catch (SQLException e) {
            System.out.println("Error SQL al eliminar Paciente" + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error al eliminar Paciente" + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Libro libro) {
        try {
            Conexion conexion1 = new Conexion();
            Connection cnx = conexion1.obtenerConexion();

            String query = "UPDATE libro set titulo = ?, autor = ?, publicacion = ?, precio = ?, disponible = ? , descripcion = ? WHERE idlibro=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setDate(3, new java.sql.Date(libro.getPublicacion().getTime()));
            stmt.setInt(4, libro.getPrecio());
            stmt.setBoolean(5, libro.isDisponible());
            stmt.setInt(6, libro.getIdLibro());
            stmt.setString(7, libro.getDescripcion());
            stmt.executeUpdate();
            stmt.close();
            cnx.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error SQL al actualizar paciente" + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error al actualizar paciente" + e.getMessage());
            return false;
        }
    }

    public Libro buscarPorId(int idLibro) {
        Libro libro = new Libro();

        try {
            Conexion conexion1 = new Conexion();
            Connection cnx = conexion1.obtenerConexion();

            String query = "SELECT idlibro, titulo, autor, publicacion, precio, disponible, descripcion FROM libro WHERE idlibro=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setInt(1, idLibro);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                libro.setIdLibro(rs.getInt("idlibro"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setPublicacion(rs.getDate("publicacion"));
                libro.setPrecio(rs.getInt("precio"));
                libro.setDisponible(rs.getBoolean("disponible"));
                libro.setDescripcion(rs.getString("descripcion"));

            }

            rs.close();
            stmt.close();
            cnx.close();
        } catch (SQLException e) {
            System.out.println("Error SQL al listar libro por id" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al listar libro por id" + e.getMessage());
        }
        return libro;
    }

    public List<Libro> buscarTodos() {
        List<Libro> lista = new ArrayList<Libro>();

        try {
            Conexion conexion1 = new Conexion();
            Connection cnx = conexion1.obtenerConexion();

            String query = "SELECT idlibro, titulo, autor, publicacion, precio, disponible,descripcion FROM libro order by titulo";
            PreparedStatement stmt = cnx.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Libro libro = new Libro();
                libro.setIdLibro(rs.getInt("idlibro"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setPublicacion(rs.getDate("publicacion"));
                libro.setPrecio(rs.getInt("precio"));
                libro.setDisponible(rs.getBoolean("disponible"));
                libro.setDescripcion(rs.getString("descripcion"));

                lista.add(libro);
            }
            rs.close();
            stmt.close();
            cnx.close();
        } catch (SQLException e) {
            System.out.println("Error SQL al listar Pacientes" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al listar pacientes" + e.getMessage());
        }
        return lista;
    
    }
    public Libro buscarPorTitulo(String titulo) {
        Libro libro = new Libro();

        try {
            Conexion conexion1 = new Conexion();
            Connection cnx = conexion1.obtenerConexion();

            String query = "SELECT idlibro, titulo, autor, publicacion, precio, disponible FROM libro WHERE UPPER(titulo)=UPPER(?)";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, titulo);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                libro.setIdLibro(rs.getInt("idlibro"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setPublicacion(rs.getDate("publicacion"));
                libro.setPrecio(rs.getInt("precio"));
                libro.setDisponible(rs.getBoolean("disponible"));

            }

            rs.close();
            stmt.close();
            cnx.close();
        } catch (SQLException e) {
            System.out.println("Error SQL al listar libro por id" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al listar libro por id" + e.getMessage());
        }
        return libro;
    }
     public Libro Contar(int precio){
         Libro libro = new Libro();
     
        try{
            Conexion conexion1 = new Conexion();
            Connection cnx = conexion1.obtenerConexion();
            
            String query ="select count(precio) from libro";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setInt(1,precio);
            ResultSet rs = stmt.executeQuery();
             if (rs.next()) {              
                libro.setPrecio(rs.getInt("precio"));
             
            }              

            rs.close();
            stmt.close();
            cnx.close();
        } catch (SQLException e) {
            System.out.println("Error SQL al listar libro por id" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al listar libro por id" + e.getMessage());
        }
        return libro;
     }
}