package exercise.mapper;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

// BEGIN
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ProductMapper {
    @Mapping(target = "name", source = "title")
    @Mapping(target = "cost", source = "price")
    @Mapping(target = "barcode", source = "vendorCode")
    public abstract Product map(ProductCreateDTO data); //из DTO в сущность

    @Mapping(target = "title", source = "name")
    @Mapping(target = "price", source = "cost")
    @Mapping(target = "vendorCode", source = "barcode")
    public abstract ProductDTO map(Product model); //из сущности в DTO для рендеринга

    @Mapping(target = "cost", source = "price")
    public abstract void update(ProductUpdateDTO data, @MappingTarget Product model);
}
// END
