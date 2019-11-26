createMeal
curl -X POST -H "Content-Type: application/json" -d "{\"dateTime\":\"2018-07-01T18:00:00\",\"description\":\"Description\",\"calories\":300}" http://localhost:8079/rest/meals

updateMeal
curl -X PUT -H "Content-Type: application/json" -d "{ \"id\": 100002, \"dateTime\": \"2015-05-30T10:00:00\", \"description\": \"Update Meal\", \"calories\": 300 }" http://localhost:8079/rest/meals/100002

getAllMeals
curl -X GET http://localhost:8079/rest/meals

getMeal
curl -X GET http://localhost:8079/rest/meals/100002

deleteMeal
curl -X DELETE http://localhost:8079/rest/meals/100002
  
getMealBetween
curl -X GET "http://localhost:8079/rest/meals/filter?startDate=&endDate=&startTime=&endTime="