# yanport install

 `gradle clean`

# yanport run

`cd aspirateur && gradle clean && ./gradlew bootRun`

# yanport test to postman

    -- server http://localhost
    -- port : 8080
    -- type request: POST
    -- path "/aspirateur
    -- body : json

# yanport illustration

    ![./src/assets/illustration.png]

# Test with gatling 
After running program to do:
 `cd integration-tests && gradle clean && gradle gatlingRun` 