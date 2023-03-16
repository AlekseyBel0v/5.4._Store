public interface IManufacturerBase {
    IManufacturerBase addManufacturer(String groupName, Country country);
    Manufacturer getManufacturer(String groupName) throws IllegalArgumentException;
}
