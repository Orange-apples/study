package com.designmodel.buidler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class House {
    private Type type;
    private Model model;
    private Furniture furniture;

    public House(HouseBuilder houseBuilder) {
        this.type = houseBuilder.type;
        this.model = houseBuilder.model;
        this.furniture = houseBuilder.furniture;
    }

    static class HouseBuilder {
        private Type type;
        private Model model;
        private Furniture furniture;

        private HouseBuilder() {
        }

        private static HouseBuilder houseBuilder = new HouseBuilder();

        public static HouseBuilder builder() {
            return houseBuilder;
        }

        public HouseBuilder withType(Type type) {
            this.type = type;
            return this;
        }

        public HouseBuilder withModel(Model model) {
            this.model = model;
            return this;
        }

        public HouseBuilder withFurniture(Furniture furniture) {
            this.furniture = furniture;
            return this;
        }

        public House build() {
            return new House(this);
        }


    }


    enum Type {
        wafang, loufang,
    }

    enum Model {
        SIMPLE, delicate, luxury
    }

    enum Furniture {
        simple, wood, stone
    }


    public static void main(String[] args) {
        House build = HouseBuilder.builder().withFurniture(Furniture.simple).withType(Type.wafang).withModel(Model.SIMPLE).build();
        System.out.println(build);
    }
}
