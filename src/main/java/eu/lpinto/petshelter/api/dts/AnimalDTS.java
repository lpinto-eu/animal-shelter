package eu.lpinto.petshelter.api.dts;

import eu.lpinto.petshelter.entities.Animal;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author yrs
 */
public enum AnimalDTS implements DTS<Animal, String> {

    SINGLETON;

    @Override
    public String tranform(Animal input) {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        sb.append(input.getId()).append(";");
        sb.append(input.getName()).append(";");
        sb.append(input.getOrganization()).append(";");
        sb.append(input.getAge() == null ? "" : input.getAge()).append(";");
        sb.append(input.getBreed() == null ? "" : input.getBreed()).append(";");
        sb.append(input.getSex() == null ? "" : input.getSex()).append(";");
        sb.append(input.getSpecies() == null ? "" : input.getSpecies()).append(";");
        sb.append(input.getDrugs() == null ? "" : input.getDrugs()).append(";");
        //sb.append(animal.getPicture()).append(";");
        sb.append(sdf.format(input.getCreated().getTime())).append(";");
        sb.append(sdf.format(input.getUpdated().getTime()));

        return sb.toString();
    }

    public String tranform(List<Animal> in) {
        if (in == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (Animal animal : in) {
            sb.append(tranform(animal));
            counter++;

            if (counter != in.size()) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }

}
