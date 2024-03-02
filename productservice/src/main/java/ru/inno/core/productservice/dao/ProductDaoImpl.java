package ru.inno.core.productservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.inno.core.productservice.dtos.SQLQueries;
import ru.inno.core.productservice.entities.ProductEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return jdbcTemplate.query(SQLQueries.SQL_GET_ALL_PRODUCTS, new ProductMapper());
    }

    @Override
    public List<ProductEntity> getProductsByUserId(Long id) {
        Object[] params = {id};
        return jdbcTemplate.query(SQLQueries.SQL_GET_PRODUCTS_BY_USER, params, new ProductMapper());
    }

    @Override
    public List<ProductEntity> getProductByProductId(Long id) {
        Object[] params = {id};
        return jdbcTemplate.query(SQLQueries.SQL_GET_PRODUCTS_BY_ID, params, new ProductMapper());
    }

    @Override
    public void addProductByUser(Long userid, ProductEntity product) {
        Object[] params = {product.getProductId(), product.getAccountNumber(), product.getBalans(), product.getAccType(), userid};
        int[] types = {Types.BIGINT, Types.BIGINT, Types.BIGINT, Types.VARCHAR, Types.BIGINT,};
        jdbcTemplate.update(SQLQueries.SQL_ADD_PRODUCTS_BY_USER, params, types);
    }

    private static final class ProductMapper implements RowMapper<ProductEntity> {
        @Override
        public ProductEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ProductEntity(rs.getLong("product_id"), rs.getLong("account"), rs.getLong("balans"), ProductEntity.AccType.valueOf(rs.getString("product_type")));
        }
    }

}
