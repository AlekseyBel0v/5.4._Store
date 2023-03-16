import java.util.HashMap;
import java.util.Map;

public class ManufacturerBase implements IManufacturerBase {
    private Map<String, Manufacturer> manufacturers = new HashMap<>();

    @Override
    public IManufacturerBase addManufacturer(String groupName, Country country) {
        if (manufacturers.containsKey(groupName)) {
            System.out.println("Организация с имененм" + groupName + " не создана, так как уже есть такое название!");
            return this;
        }
        manufacturers.put(groupName, new Manufacturer(groupName, country));
        return this;
    }

    @Override
    public Manufacturer getManufacturer(String groupName) throws IllegalArgumentException {
        if (!manufacturers.containsKey(groupName)) {
            throw new IllegalArgumentException("Организации с имненем " + groupName + " не найдена!");
        }
        return manufacturers.get(groupName);
    }
}
