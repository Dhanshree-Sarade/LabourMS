$("#login").on('click', function(e) {
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


