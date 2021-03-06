package com.tngtech.jgiven.examples.pancakes.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tngtech.jgiven.examples.pancakes.test.steps.GivenIngredients;
import com.tngtech.jgiven.examples.pancakes.test.steps.ThenMeal;
import com.tngtech.jgiven.examples.pancakes.test.steps.WhenCook;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = TestSpringConfig.class )
public class SpringPanCakeScenarioTest extends SpringScenarioTest<GivenIngredients, WhenCook, ThenMeal> {

    @Test
    public void a_pancake_can_be_fried_out_of_an_egg_milk_and_flour() {
        given().an_egg().
            and().some_milk().
            and().the_ingredient( "flour" );

        when().the_cook_mangles_everthing_to_a_dough().
            and().the_cook_fries_the_dough_in_a_pan();

        then().the_resulting_meal_is_a_pan_cake();
    }

}
