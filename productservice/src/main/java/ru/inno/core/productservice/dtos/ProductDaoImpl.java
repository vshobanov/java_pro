package ru.inno.core.productservice.dtos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.inno.core.productservice.entities.ProductEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Component
public class ProductDaoImpl implements ProductDto {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ProductEntity> getAllProducts() {

        String sql = SQLQueries.SQL_GET_ALL_PRODUCTS;
        List<ProductEntity> productEntities = jdbcTemplate.query(sql, new ProductMapper());
        return productEntities.isEmpty() ? null : productEntities;
    }

    @Override
    public List<ProductEntity> getProductsByUserId(Long id) {
        String sql = SQLQueries.SQL_GET_PRODUCTS_BY_USER;
        Object[] params = {id};
        List<ProductEntity> productEntities = jdbcTemplate.query(sql, params, new ProductMapper());
        return productEntities.isEmpty() ? null : productEntities;
    }

    @Override
    public List<ProductEntity> getProductByProductId(Long id) {
        String sql = SQLQueries.SQL_GET_PRODUCTS_BY_ID;
        Object[] params = {id};
        return jdbcTemplate.query(sql, params, new ProductMapper());
    }

    @Override
    public void addProductByUser(Long userid, ProductEntity product) {
        String sql = SQLQueries.SQL_ADD_PRODUCTS_BY_USER;
        Object[] params = {product.getProductId(), product.getAccountNumber(),product.getBalans(),product.getAccType(),userid};
        int[] types = {Types.BIGINT, Types.BIGINT,Types.BIGINT,Types.VARCHAR,Types.BIGINT,};
        jdbcTemplate.update(sql, params, types);
    }

    private static final class ProductMapper implements RowMapper<ProductEntity> {
        @Override
        public ProductEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ProductEntity(rs.getLong("product_id"), rs.getLong("account"), rs.getLong("balans"), rs.getString("product_type"));
        }
    }

}
