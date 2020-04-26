package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    final static String NAME_TAG = "name";
    final static String MAINNAME_TAG = "mainName";
    final static String ALSOKNOWNAS_TAG = "alsoKnownAs";
    final static String PLACEOFORIGIN_TAG = "placeOfOrigin";
    final static String DESCRIPTION_TAG = "description";
    final static String IMAGE_TAG = "image";
    final static String INGREDIENTS_TAG = "ingredients";


    public static Sandwich parseSandwichJson(String json) {
        Sandwich returnValue = null;
        try {
            returnValue = new Sandwich();
            JSONObject object = new JSONObject(json);
            JSONObject main = object.getJSONObject(NAME_TAG);
            String mainName = main.getString(MAINNAME_TAG);
            returnValue.setMainName(mainName);

            JSONArray alsoKnownAs = main.getJSONArray(ALSOKNOWNAS_TAG);
            List<String> alsoKnownAsList = new ArrayList<String>();
            for (int nfor = 0; nfor < alsoKnownAs.length(); ++nfor) {
                alsoKnownAsList.add(alsoKnownAs.getString(nfor));
            }
            returnValue.setAlsoKnownAs(alsoKnownAsList);

            String placeOfOrigin = object.getString(PLACEOFORIGIN_TAG);
            returnValue.setPlaceOfOrigin(placeOfOrigin);

            String description = object.getString(DESCRIPTION_TAG);
            returnValue.setDescription(description);


            String imageURL = object.getString(IMAGE_TAG);
            returnValue.setImage(imageURL);

            JSONArray ingredients = object.getJSONArray(INGREDIENTS_TAG);
            List<String> ingredientsList = new ArrayList<String>();
            for(int i = 0; i < ingredients.length(); ++i) {
                ingredientsList.add(ingredients.getString(i));
            }
            returnValue.setIngredients(ingredientsList);

        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return returnValue;
        }

    }
}
