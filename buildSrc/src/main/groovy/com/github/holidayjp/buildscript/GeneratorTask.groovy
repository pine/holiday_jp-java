package com.github.holidayjp.buildscript

import org.yaml.snakeyaml.Yaml

import java.nio.file.Paths
import java.text.SimpleDateFormat

class GeneratorTask {
    static run(String rootDir) {
        def parser = new Yaml()
        def dataSheetFile = Paths.get(rootDir, 'datasheet/holidays_detailed.yml').toFile()
        def holidaysDetailed = parser.load(dataSheetFile.text)
        def holidays = holidaysDetailed.values().sort { lhs, rhs -> lhs.date <=> rhs.date }

        def outputFile = Paths.get(rootDir, "holidayjp/src/main/java/com/github/holidayjp/Holidays.java").toFile()
        def sdf = new SimpleDateFormat("yyyy-MM-dd")
        outputFile.newWriter().withCloseable { writer ->
            writer.println("package com.github.holidayjp;")
            writer.println()
            writer.println("import java.util.HashMap;")
            writer.println("import java.util.Map;")
            writer.println()
            writer.println("import javax.annotation.Nonnull;")
            writer.println()
            writer.println("/**")
            writer.println(" * Holidays")
            writer.println(" * Generated by GeneratorTask.groovy")
            writer.println(" */")
            writer.println("public class Holidays {")
            writer.println("    @Nonnull")
            writer.println("    public static final Map<String, Holiday> HOLIDAYS;")
            writer.println()
            writer.println("    static {")
            writer.println("        HOLIDAYS = new HashMap<>();")
            writer.println()

            for (holiday in holidays) {
                def ymd = sdf.format(holiday.date)
                writer.write("        HOLIDAYS.put(\"$ymd\", new Holiday(")
                writer.write("\"$ymd\", \"$holiday.week\", \"$holiday.week_en\", \"$holiday.name\",\"$holiday.name_en\"")
                writer.write("));\n")
            }

            writer.println("    }")
            writer.println()
            writer.println("}")


        }
    }
}
