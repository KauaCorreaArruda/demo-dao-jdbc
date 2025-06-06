package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT seller.*, department.name AS depname " +
                            "FROM seller " +
                            "INNER JOIN department ON seller.department_id = department.id " +
                            "WHERE seller.id = ? " );

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Department department = instanciateDepartment(rs);
                Seller object = instanciateSeller(rs, department);

                return object;
            }

            return null;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT seller.*,department.name as depname " +
                            "FROM seller INNER JOIN department " +
                            "ON seller.department_id = department.id " +
                            "WHERE department_id = ? " +
                            "ORDER BY name" );

            st.setInt(1, department.getId());
            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {

                department = map.get(rs.getInt("department_id" ));

                if (department == null) {
                department = instanciateDepartment(rs);
                map.put(rs.getInt("department_id"), department);
                }

                Seller object = instanciateSeller(rs, department);
                list.add(object);
            }

            return list;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Seller instanciateSeller(ResultSet rs, Department department) throws SQLException {
        Seller object = new Seller();
        object.setId(rs.getInt("id"));
        object.setName(rs.getString("name"));
        object.setEmail(rs.getString("email"));
        object.setBaseSalary(rs.getDouble("base_salary"));
        object.setBirthDate(rs.getDate("birth_date"));
        object.setDepartment(department);

        return object;
    }

    private Department instanciateDepartment(ResultSet rs) throws SQLException {
        Department department = new Department();
        department.setId(rs.getInt("department_id"));
        department.setName(rs.getString("depname"));

        return department;
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT seller.*,department.name as depname " +
                            "FROM seller INNER JOIN department " +
                            "ON seller.department_id = department.id " +
                            "ORDER BY name" );

            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {

               Department department = map.get(rs.getInt("department_id" ));

                if (department == null) {
                    department = instanciateDepartment(rs);
                    map.put(rs.getInt("department_id"), department);
                }

                Seller object = instanciateSeller(rs, department);
                list.add(object);
            }

            return list;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
