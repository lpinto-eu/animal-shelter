package eu.lpinto.petshelter.api.dts;

import eu.lpinto.petshelter.persistence.entities.Animal;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author mostardinha - pedro.mostardinha@gmail.com
 */
public enum AnimalsDTS implements DTS<Animal, String> {

    SINGLETON;

    @Override
    public String transform(Animal input) {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        sb.append(input.getId()).append(";");
        sb.append(input.getName()).append(";");
        sb.append(input.getOrganization()).append(";");
        sb.append(input.getAge() == null ? "" : input.getAge()).append(";");
        sb.append(input.getBreed() == null ? "" : input.getBreed()).append(";");
        sb.append(input.getFurPattern() == null ? "" : input.getFurPattern()).append(";");
        sb.append(input.getWeight()).append(";");
        sb.append(input.getProportion() == null ? "" : input.getProportion()).append(";");
        sb.append(input.getPrimaryColor() == null ? "" : input.getPrimaryColor()).append(";");
        sb.append(input.getSecondaryColor() == null ? "" : input.getSecondaryColor()).append(";");
        sb.append(input.getSex() == null ? "" : input.getSex()).append(";");
        sb.append(input.getStatus() == null ? "" : input.getStatus()).append(";");
        sb.append(input.getSpecies() == null ? "" : input.getSpecies()).append(";");
        sb.append(input.getDrugs() == null ? "" : input.getDrugs()).append(";");
        //sb.append(animal.getPicture()).append(";");
        sb.append(sdf.format(input.getCreated().getTime())).append(";");
        sb.append(sdf.format(input.getUpdated().getTime())).append(";");
        sb.append(input.getObservations());
        sb.append(input.getAdmission() == null ? "" : sdf.format(input.getAdmission().getTime())).append(";");

        return sb.toString();
    }

    public String transform(List<Animal> in) {
        if (in == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (Animal animal : in) {
            sb.append(transform(animal));
            counter++;

            if (counter != in.size()) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }

}
