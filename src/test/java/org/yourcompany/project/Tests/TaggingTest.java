//INSTALL MAVEN BIN FOLDER AND ADD TO ENVIONMENT PATH VARIABLE

package org.yourcompany.project.Tests;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

//to run tests with both tags or one specific tag: mvn test -Dgroups="fast,model" 
//to avoid running tests with a specific tag use :mvn test -DexcludeGroups="tagname"

// 👇 This class has two tags: "fast" and "model"
@Tag("fast")
@Tag("model")
class TaggingTest {

    // 👇 This test method has its own tag: "taxes"
    @Test
    @Tag("taxes")
    void testingTaxCalculation() {
        System.out.println("Running tax calculation test!");
    }

    @Test
    @Tag("billing")
    void testingBillingFeature() {
        System.out.println("Running billing test!");
    }

    @Test
    @Tag("slow")
    void testingBigReport() {
        System.out.println("Running big report test!");
    }
}
