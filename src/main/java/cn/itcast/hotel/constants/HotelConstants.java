package cn.itcast.hotel.constants;

public class HotelConstants {
    public static final String MAPPING_TEMPLATE="{\n" +
            "\t\"mapping\":{\n" +
            "\t\t\"properties\":{\n" +
            "\t\t\t\"id\":{\n" +
            "\t\t\t\t\"type\":\"keyword\"\n" +
            "\t\t\t\t}，\n" +
            "\t\t\t\"name\":{\n" +
            "\t\t\t\t\"type\":\"text\",\n" +
            "\t\t\t\t\"analyzer\":\"ik_max_word\",\n" +
            "\t\t\t\t\"copy-to\":\"all\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\"address\":{\n" +
            "\t\t\t\t\"type\":\"keyword\",\n" +
            "\t\t\t\t\"index\":\"flase\"\n" +
            "\t\t\t\t}，\n" +
            "\t\t\t\"price\":{\n" +
            "\t\t\t\t\"type\":\"integer\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\"score\":{\n" +
            "\t\t\t\t\"type\":\"integer\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\"brand\":{\n" +
            "\t\t\t\t\"type\":\"keyword\",\n" +
            "\t\t\t\t\"copy-to\":\"all\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\"city\":{\n" +
            "\t\t\t\t\"type\":\"keyword\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\"starName\":{\n" +
            "\t\t\t\t\"type\":\"keyword\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\"business\":{\n" +
            "\t\t\t\t\"type\":\"keyword\"\n" +
            "\t\t\t\t}，\n" +
            "\t\t\t\"location\":{\n" +
            "\t\t\t\t\"type\":\"geo_point\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\"pic\":{\n" +
            "\t\t\t\t\"type\":\"keyword\",\n" +
            "\t\t\t\t\"index\":\"flase\"\n" +
            "\t\t\t\t}，\n" +
            "\t\t\t\"all\":{\n" +
            "\t\t\t\t\"type\":\"text\",\n" +
            "\t\t\t\t\"index\":\"ik_max_word\"\n" +
            "\t\t\t\t}\n" +
            "\t\t}\n" +
            "\t}\n" +
            "}\n" +
            "\t\t\t\t";
}
