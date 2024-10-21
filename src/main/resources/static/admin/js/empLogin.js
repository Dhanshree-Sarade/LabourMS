$("#login").on('click', function(e) {
    e.preventDefault();

    let email = $("input[name='email']").val();
    let password = $("input[name='password']").val();

    let loginDetails = {
        email: email,
        password: password
    };


    $.ajax({
        type: "POST",
        url: "/login", // Endpoint for login
        contentType: "application/json",
        data: JSON.stringify(loginDetails),
        success: function(response) {
            // If the login is successful, store data in localStorage
            localStorage.setItem('empId', response.id);
            localStorage.setItem('email', response.email);
            localStorage.setItem('password', response.password); // You can skip storing password if it's sensitive
            
             let loggedInUser = {
                id: response.id,
                email: response.email,
                password: response.password
            };
            localStorage.setItem('loggedInUser', JSON.stringify(loggedInUser));

            // Redirect to the target URL
            window.location.href = response.redirectUrl;
        },
        error: function() {
            alert("Invalid login credentials.");
        }
    });
});



/*$("#login").on('click', function(e) {
	alert("Button Clicked....");
    e.preventDefault();

    let email = $("input[name='email']").val();
    let password = $("input[name='password']").val();

    let details = {
        email: email,
        password: password
    };
	console.log("Login Details : ", details);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: '/login', 
        data: JSON.stringify(details), 
        dataType: 'json',
        success: function(response) {
			alert("Login Successful..");
            if (response.redirectUrl) {
                window.location.href = response.redirectUrl;
            } else {
                alert("Invalid username or password.");
            }
        },
        error: function(e) {
            alert("An error occurred while logging in.");
        }
    });
});


*/