/*$("#empSave").on('click', function(e) {
	alert("Button Clicked....");
    e.preventDefault();

    let fName = $("input[name='fName']").val();
    let lName = $("input[name='lName']").val();
    let mobile_no = $("input[name='mobile_no']").val();
    let address = $("input[name='address']").val();
    let designation = $("select[name='designation']").val();
    let email = $("input[name='email']").val();
    let password = $("input[name='password']").val();
    let joining_date = $("input[name='joiningDate']").val();
    let salary = $("input[name='salary']").val();

    let details = {
        fName: fName,
        lName: lName,
        mobile_no:mobile_no,
        address:address,
        designation:designation,
        email:email,
        password:password,
        joining_date:joining_date,
        salary:salary
    };
	console.log("Login Details : ", details);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: '/employees', 
        data: JSON.stringify(details), 
        dataType: 'json',
        success: function(response) {
			alert("Employee Added Successful..");
			 $("#registerForm").trigger("reset");
        },
        error: function(e) {
            alert("Employee not Added..");
        }
    });
});*/

/*$("#empSave").on('click', function(e) {
	alert("Added..")
    e.preventDefault();
    
    let isValid = true;

    // Fetch input values
    let fName = $("input[name='fName']").val();
    let lName = $("input[name='lName']").val();
    let mobile_no = $("input[name='mobile_no']").val();
    let address = $("input[name='address']").val();
    let designation = $("select[name='designation']").val();
    let email = $("input[name='email']").val();
    let password = $("input[name='password']").val();
    let joining_date = $("input[name='joiningDate']").val();
    let salary = $("input[name='salary']").val();
    let gender = $("select[name='gender']").val();
    let birthDate = $("input[name='birthDate']").val();
    let bloodGroup = $("select[name='bloodGroup']").val();
    let maritalStatus = $("select[name='maritalStatus']").val();
    let resigningDate = $("input[name='resigningDate']").val();
    let employeeStatus = $("select[name='employeeStatus']").val();
    let bankAccountNo = $("input[name='bankAccountNo']").val();
    let bankName = $("input[name='bankName']").val();
    let ifscCode = $("input[name='ifscCode']").val();
    let accountType = $("select[name='accountType']").val();
    let document = $("input[name='document']")[0].files[0]; // For the document file

    // Clear any previous validation error messages
    $(".error-msg").remove();

    // Regular expressions for validation
    let namePattern = /^[A-Za-z\s]+$/; // For First Name, Last Name (Alphabets and spaces only)
    let addressPattern = /^[A-Za-z0-9\s,.-]+$/; // For Address (Allowing characters, numbers, spaces, commas, periods, hyphens)
    let emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    let mobilePattern = /^[0-9]{10}$/; // For Mobile Number

    // Validate First Name
    if (!fName || fName.trim().length < 2 || !namePattern.test(fName)) {
        isValid = false;
        $("input[name='fName']").after('<span class="error-msg" style="color:red;">First Name is required (min 2 characters, alphabets only)</span>');
    }

    // Validate Last Name
    if (!lName || lName.trim().length < 2 || !namePattern.test(lName)) {
        isValid = false;
        $("input[name='lName']").after('<span class="error-msg" style="color:red;">Last Name is required (min 2 characters, alphabets only)</span>');
    }

    // Validate Mobile Number
    if (!mobile_no || !mobilePattern.test(mobile_no)) {
        isValid = false;
        $("input[name='mobile_no']").after('<span class="error-msg" style="color:red;">Enter a valid 10-digit Mobile Number</span>');
    }

    // Validate Address
    if (!address || address.trim().length < 5 || !addressPattern.test(address)) {
        isValid = false;
        $("input[name='address']").after('<span class="error-msg" style="color:red;">Address must be at least 5 characters long (alphanumeric and symbols: ,.-)</span>');
    }

    // Validate Designation
    if (!designation) {
        isValid = false;
        $("select[name='designation']").after('<span class="error-msg" style="color:red;">Please select a designation</span>');
    }

    // Validate Email
    if (!email || !emailPattern.test(email)) {
        isValid = false;
        $("input[name='email']").after('<span class="error-msg" style="color:red;">Enter a valid Email</span>');
    }

    // Validate Password
    if (!password || password.trim().length < 6) {
        isValid = false;
        $("input[name='password']").after('<span class="error-msg" style="color:red;">Password must be at least 6 characters long</span>');
    }

    // Validate Joining Date
    if (!joining_date) {
        isValid = false;
        $("input[name='joiningDate']").after('<span class="error-msg" style="color:red;">Joining Date is required</span>');
    }

    // Validate Salary
    if (!salary || salary <= 0) {
        isValid = false;
        $("input[name='salary']").after('<span class="error-msg" style="color:red;">Please enter a valid Salary</span>');
    }

    // Validate Document File
    if (!document) {
        isValid = false;
        $("input[name='document']").after('<span class="error-msg" style="color:red;">Please upload a document</span>');
    }

    // If form is valid, proceed with AJAX request
    if (isValid) {
        let formData = new FormData();
        formData.append("fName", fName);
        formData.append("lName", lName);
        formData.append("mobile_no", mobile_no);
        formData.append("address", address);
        formData.append("designation", designation);
        formData.append("email", email);
        formData.append("password", password);
        formData.append("joining_date", joining_date);
        formData.append("salary", salary);
        formData.append("status", employeeStatus);
        formData.append("document", document); // Append the document file
        formData.append("gender", gender);
        formData.append("birthDate", birthDate);
        formData.append("bloodGroup", bloodGroup);
        formData.append("maritalStatus", maritalStatus);
        formData.append("resigningDate", resigningDate);
        formData.append("bankAccountNo", bankAccountNo);
        formData.append("bankName", bankName);
        formData.append("ifscCode", ifscCode);
        formData.append("accountType", accountType);

        $.ajax({
            type: "POST",
            url: '/employees',
            data: formData,
            processData: false, // Prevent jQuery from processing the data
            contentType: false, // Let the browser set the appropriate content type for FormData
            success: function(response) {
                alert("Employee Added Successfully.");
                $("#registerForm").trigger("reset");
            },
            error: function(e) {
                alert("Error adding employee.");
            }
        });
    } else {
        alert("Please correct the errors and submit again.");
    }
});*/


$("#empSave").on('click', function(e) {
    e.preventDefault();
    
    let isValid = true;

    // Fetch input values
    let fName = $("input[name='fName']").val();
    let lName = $("input[name='lName']").val();
    let mobile_no = $("input[name='mobile_no']").val();
    let address = $("input[name='address']").val();
    let designation = $("select[name='designation']").val();
    let email = $("input[name='email']").val();
    let password = $("input[name='password']").val();
    let joining_date = $("input[name='joiningDate']").val();
    let salary = $("input[name='salary']").val();
    let status = $("select[name='status']").val();
    let gender = $("select[name='gender']").val();
    let birthDate = $("input[name='birthDate']").val();
    
    let bloodGroup = $("select[name='bloodGroup']").val();
    let maritalStatus = $("select[name='maritalStatus']").val();
    let qualification = $("input[name='qualification']").val();
    let resigning_date = $("input[name='resigningDate']").val();
    let bankAccountNo = $("input[name='bankAccountNo']").val();
    let bankName = $("input[name='bankName']").val();
    let ifscCode = $("input[name='ifscCode']").val();
     let accountType = $("select[name='accountType']").val();
    
    let file = $("input[name='documentName[]']")[0].files[0]; // Assuming there's an input for file upload

    // Clear any previous validation error messages
    $(".error-msg").remove();

    // Regular expressions for validation
    let namePattern = /^[A-Za-z\s]+$/; // For First Name, Last Name (Alphabets and spaces only)
    let addressPattern = /^[A-Za-z0-9\s,.-]+$/; // For Address (Allowing characters, numbers, spaces, commas, periods, hyphens)

    // Validate First Name
    if (!fName || fName.trim().length < 2 || !namePattern.test(fName)) {
        isValid = false;
        $("input[name='fName']").after('<span class="error-msg" style="color:red;">First Name is required (min 2 characters, alphabets only)</span>');
    }

    // Validate Last Name
    if (!lName || lName.trim().length < 2 || !namePattern.test(lName)) {
        isValid = false;
        $("input[name='lName']").after('<span class="error-msg" style="color:red;">Last Name is required (min 2 characters, alphabets only)</span>');
    }

    // Validate Mobile Number (Basic format check)
    let mobilePattern = /^[0-9]{10}$/;
    if (!mobile_no || !mobilePattern.test(mobile_no)) {
        isValid = false;
        $("input[name='mobile_no']").after('<span class="error-msg" style="color:red;">Enter a valid 10-digit Mobile Number</span>');
    }

    // Validate Address
    if (!address || address.trim().length < 5 || !addressPattern.test(address)) {
        isValid = false;
        $("input[name='address']").after('<span class="error-msg" style="color:red;">Address must be at least 5 characters long (alphanumeric and symbols: ,.-)</span>');
    }

    // Validate Designation
    if (!designation) {
        isValid = false;
        $("select[name='designation']").after('<span class="error-msg" style="color:red;">Please select a designation</span>');
    }

    // Validate Email (Basic format check)
    let emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    if (!email || !emailPattern.test(email)) {
        isValid = false;
        $("input[name='email']").after('<span class="error-msg" style="color:red;">Enter a valid Email</span>');
    }

    // Validate Password
    if (!password || password.trim().length < 6) {
        isValid = false;
        $("input[name='password']").after('<span class="error-msg" style="color:red;">Password must be at least 6 characters long</span>');
    }

    // Validate Joining Date
    if (!joining_date) {
        isValid = false;
        $("input[name='joiningDate']").after('<span class="error-msg" style="color:red;">Joining Date is required</span>');
    }

    // Validate Salary
    if (!salary || salary <= 0) {
        isValid = false;
        $("input[name='salary']").after('<span class="error-msg" style="color:red;">Please enter a valid Salary</span>');
    }

    // If form is valid, proceed with AJAX request
    if (isValid) {
        // Create a FormData object
        let formData = new FormData();
        formData.append("fName", fName);
        formData.append("lName", lName);
        formData.append("mobile_no", mobile_no);
        formData.append("address", address);
        formData.append("designation", designation);
        formData.append("email", email);
        formData.append("password", password);
        formData.append("joining_date", joining_date);
        formData.append("salary", salary);
        formData.append("status", status);
        formData.append("gender", gender);
        formData.append("birthDate", birthDate);
        formData.append("bloodGroup", bloodGroup);
        formData.append("maritalStatus", maritalStatus);
        formData.append("qualification", qualification);
        formData.append("resigning_date", resigning_date);
        formData.append("bankAccountNo", bankAccountNo);
        formData.append("bankName", bankName);
        formData.append("ifscCode", ifscCode);
        formData.append("accountType", accountType);
        
        if (file) formData.append("file", file); // Add the file to FormData if it exists

        $.ajax({
            type: "POST",
            url: '/employees',
            data: formData,
            processData: false, // Prevent jQuery from processing the data
            contentType: false, // Let the browser set the content type
            success: function(response) {
                alert("Employee Added Successfully.");
                $("#registerForm").trigger("reset");
            },
            error: function(e) {
                alert("Error adding employee: " + e.responseText); // Show the error message
            }
        });
    } else {
        alert("Please correct the errors and submit again.");
    }
});






/*$("#empSave").on('click', function(e) {
    e.preventDefault();
    
    let isValid = true;

    // Fetch input values
    let fName = $("input[name='fName']").val();
    let lName = $("input[name='lName']").val();
    let mobile_no = $("input[name='mobile_no']").val();
    let address = $("input[name='address']").val();
    let designation = $("select[name='designation']").val();
    let email = $("input[name='email']").val();
    let password = $("input[name='password']").val();
    let joining_date = $("input[name='joiningDate']").val();
    let salary = $("input[name='salary']").val();

    // Clear any previous validation error messages
    $(".error-msg").remove();

    // Regular expressions for validation
    let namePattern = /^[A-Za-z\s]+$/; // For First Name, Last Name (Alphabets and spaces only)
    let addressPattern = /^[A-Za-z0-9\s,.-]+$/; // For Address (Allowing characters, numbers, spaces, commas, periods, hyphens)

    // Validate First Name
    if (!fName || fName.trim().length < 2 || !namePattern.test(fName)) {
        isValid = false;
        $("input[name='fName']").after('<span class="error-msg" style="color:red;">First Name is required (min 2 characters, alphabets only)</span>');
    }

    // Validate Last Name
    if (!lName || lName.trim().length < 2 || !namePattern.test(lName)) {
        isValid = false;
        $("input[name='lName']").after('<span class="error-msg" style="color:red;">Last Name is required (min 2 characters, alphabets only)</span>');
    }

    // Validate Mobile Number (Basic format check)
    let mobilePattern = /^[0-9]{10}$/;
    if (!mobile_no || !mobilePattern.test(mobile_no)) {
        isValid = false;
        $("input[name='mobile_no']").after('<span class="error-msg" style="color:red;">Enter a valid 10-digit Mobile Number</span>');
    }

    // Validate Address
    if (!address || address.trim().length < 5 || !addressPattern.test(address)) {
        isValid = false;
        $("input[name='address']").after('<span class="error-msg" style="color:red;">Address must be at least 5 characters long (alphanumeric and symbols: ,.-)</span>');
    }

    // Validate Designation
    if (!designation) {
        isValid = false;
        $("select[name='designation']").after('<span class="error-msg" style="color:red;">Please select a designation</span>');
    }

    // Validate Email (Basic format check)
    let emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    if (!email || !emailPattern.test(email)) {
        isValid = false;
        $("input[name='email']").after('<span class="error-msg" style="color:red;">Enter a valid Email</span>');
    }

    // Validate Password
    if (!password || password.trim().length < 6) {
        isValid = false;
        $("input[name='password']").after('<span class="error-msg" style="color:red;">Password must be at least 6 characters long</span>');
    }

    // Validate Joining Date
    if (!joining_date) {
        isValid = false;
        $("input[name='joiningDate']").after('<span class="error-msg" style="color:red;">Joining Date is required</span>');
    }

    // Validate Salary
    if (!salary || salary <= 0) {
        isValid = false;
        $("input[name='salary']").after('<span class="error-msg" style="color:red;">Please enter a valid Salary</span>');
    }

    // If form is valid, proceed with AJAX request
    if (isValid) {
        let details = {
            fName: fName,
            lName: lName,
            mobile_no: mobile_no,
            address: address,
            designation: designation,
            email: email,
            password: password,
            joining_date: joining_date,
            salary: salary
        };
        console.log("Form Details: ", details);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: '/employees',
            data: JSON.stringify(details),
            dataType: 'json',
            success: function(response) {
                alert("Employee Added Successfully.");
                $("#registerForm").trigger("reset");
            },
            error: function(e) {
                alert("Error adding employee.");
            }
        });
    } else {
        alert("Please correct the errors and submit again.");
    }
});
*/


$(document).ready(function() {

	showEmployee();
});

function showEmployee() {

	//alert("Table is loading...");
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: '/employees',
		dataType: 'json',
		success: function(data) {

			var d = '';

			for (var i = 0; i < data.length; i++) {

				console.log("show data" + data)


				d += '<tr>' +

					'<td > ' + data[i].id + '</td>' +
					'<td > ' + data[i].fName + '</td>' +
					'<td > ' + data[i].lName + '</td>' +
					'<td > ' + data[i].address + '</td>' +
					'<td > ' + data[i].mobile_no + '</td>' +
					'<td > ' + data[i].email + '</td>' +
					'<td > ' + data[i].password + '</td>' +
					'<td > ' + data[i].designation + '</td>' +
					'<td > ' + data[i].joining_date + '</td>' +
					'<td > ' + data[i].salary + '</td>' +
					'<td > ' + ' <button type="button" type="button" class="btn mb-1 btn-rounded btn-warning gradient-2" data-bs-toggle="modal" data-bs-target="#editEmp" data-table-id="' + data[i].id + '"  id="editEmp-' + data[i].id + '"><span class="btn-icon-center"><i class="bi bi-pencil-square"></i></span></button>' +
					' <button type="button" class="btn mb-1 btn-danger gradient-3" data-table-id="' + data[i].id + '" id="deleteBtn-' + data[i].id + '"> <span class="btn-icon-center"><i class="bi bi-trash"></i></span></button>' + '</td>' +

					'</tr >'
			}

			$('#empTable').html(d);

		},
		error: function() {
			alert("Error loading data...");
		}
	});
}


$(document).on('click', '[id^="deleteBtn-"]', function() {

	alert("Do you want to delete record.....");
	let id = $(this).data('table-id');
	console.log("Emp ID is : " + id);

	$.ajax({
		type: "DELETE",
		contentType: "application/json",
		url: '/employees/' + id,
		success: function(response) {
			/*toastr.success('Deleted successfully!');*/
			showEmployee();
		},
		error: function(e) {
			alert("Details not deleted...");
		}
	});

});


$(document).on('click', '[id^="editEmp-"]', function() {
	alert("Do you want to edit record.....");
	let id = $(this).data('table-id');
	console.log("Emp ID is : " + id);

	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/employees/" + id,
		dataType: 'json',
		success: function(data) {
			console.log("Data is : ", data);
			if (data) {
				$("#empId").val(data.id);
				$("#exampleInputFname2").val(data.fName);
				$("#exampleInputLName2").val(data.lName);
				$("#exampleInputMobile2").val(data.mobile_no);
				$("#exampleInputAddress2").val(data.address);
				$("#exampleInputDesignation2").val(data.designation);
				$("#exampleInputEmail2").val(data.email);
				$("#exampleInputConfirmPassword2").val(data.password);
				$("#exampleInputJoiningDate2").val(data.joining_date);
				$("#exampleInputSalary2").val(data.salary);
			}
		},
		error: function(e) {
			console.log("Error in feching data for Id....");
		}
	});
});


$('#editEmpSave').on('click', function(e) {
    e.preventDefault();
    
    let isValid = true;

    // Gather data from the input fields
    let id = $('#empId').val();
    let fName = $('#exampleInputFname2').val();
    let lName = $('#exampleInputLName2').val();
    let address = $('#exampleInputAddress2').val();
    let mobile_no = $('#exampleInputMobile2').val();
    let designation = $('#exampleInputDesignation2').val();
    let email = $('#exampleInputEmail2').val();
    let password = $('#exampleInputConfirmPassword2').val();
    let joining_date = $('#exampleInputJoiningDate2').val();
    let salary = $('#exampleInputSalary2').val();

    // Clear any previous validation error messages
    $(".error-msg").remove();

    // Regular expressions for validation
    let namePattern = /^[A-Za-z\s]+$/; // For First Name, Last Name (Alphabets and spaces only)
    let addressPattern = /^[A-Za-z0-9\s,.-]+$/; // For Address (Allowing characters, numbers, spaces, commas, periods, hyphens)

    // Validate First Name
    if (!fName || fName.trim().length < 2 || !namePattern.test(fName)) {
        isValid = false;
        $('#exampleInputFname2').after('<span class="error-msg" style="color:red;">First Name is required (min 2 characters, alphabets only)</span>');
    }

    // Validate Last Name
    if (!lName || lName.trim().length < 2 || !namePattern.test(lName)) {
        isValid = false;
        $('#exampleInputLName2').after('<span class="error-msg" style="color:red;">Last Name is required (min 2 characters, alphabets only)</span>');
    }

    // Validate Mobile Number
    let mobilePattern = /^[0-9]{10}$/;
    if (!mobile_no || !mobilePattern.test(mobile_no)) {
        isValid = false;
        $('#exampleInputMobile2').after('<span class="error-msg" style="color:red;">Enter a valid 10-digit Mobile Number</span>');
    }

    // Validate Address
    if (!address || address.trim().length < 5 || !addressPattern.test(address)) {
        isValid = false;
        $('#exampleInputAddress2').after('<span class="error-msg" style="color:red;">Address must be at least 5 characters long (alphanumeric and symbols: ,.-)</span>');
    }

    // Validate Designation
    if (!designation) {
        isValid = false;
        $('#exampleInputDesignation2').after('<span class="error-msg" style="color:red;">Please select a designation</span>');
    }

    // Validate Email
    let emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    if (!email || !emailPattern.test(email)) {
        isValid = false;
        $('#exampleInputEmail2').after('<span class="error-msg" style="color:red;">Enter a valid Email</span>');
    }

    // Validate Password
    if (!password || password.trim().length < 6) {
        isValid = false;
        $('#exampleInputConfirmPassword2').after('<span class="error-msg" style="color:red;">Password must be at least 6 characters long</span>');
    }

    // Validate Joining Date
    if (!joining_date) {
        isValid = false;
        $('#exampleInputJoiningDate2').after('<span class="error-msg" style="color:red;">Joining Date is required</span>');
    }

    // Validate Salary
    if (!salary || salary <= 0) {
        isValid = false;
        $('#exampleInputSalary2').after('<span class="error-msg" style="color:red;">Please enter a valid Salary</span>');
    }

    // If the form is valid, proceed with the AJAX request
    if (isValid) {
        var formData = new FormData();
        formData.append('id', id);
        formData.append('fName', fName);
        formData.append('lName', lName);
        formData.append('address', address);
        formData.append('mobile_no', mobile_no);
        formData.append('designation', designation);
        formData.append('password', password);
        formData.append('email', email);
        formData.append('joining_date', joining_date);
        formData.append('salary', salary);

        // Make AJAX request to update user details
        $.ajax({
            url: '/employees', // Update with the correct endpoint
            type: 'PUT',
            processData: false, // Prevent jQuery from automatically transforming the data into a query string
            contentType: false, // Let the content type be set by FormData
            data: formData,
            success: function(response) {
                alert('User updated successfully.');
                $('#editEmp').modal('hide');
            },
            error: function(xhr, status, error) {
                console.log("Error updating user: ", xhr.responseText);
                alert('Error updating user: ' + xhr.responseText);
            }
        });
    } else {
        alert("Please correct the errors and submit again.");
    }
});


/*$('#editEmpSave').on('click', function() {
	// Gather data from the input fields
	let id = $('#empId').val();
	let fName = $('#exampleInputFname2').val();
    let lName = $('#exampleInputLName2').val();
    let address = $('#exampleInputAddress2').val();
    let mobile_no = $('#exampleInputMobile2').val();
    let designation = $('#exampleInputDesignation2').val();
    let email = $('#exampleInputEmail2').val();
    let password = $('#exampleInputConfirmPassword2').val();
    let joining_date = $('#exampleInputJoiningDate2').val();
    let salary = $('#exampleInputSalary2').val();
	
	
	var formData = new FormData();
	formData.append('id', id);
	formData.append('fName', fName);
	formData.append('lName', lName);
	formData.append('address', address);
	formData.append('mobile_no', mobile_no);
	formData.append('designation', designation);
	formData.append('password', password);
	formData.append('email', email);
	formData.append('joining_date', joining_date);
	formData.append('salary', salary);


	// Make AJAX request to update user details
	$.ajax({
		url: '/employees', // Update with the correct endpoint
		type: 'PUT',
		processData: false, // Prevent jQuery from automatically transforming the data into a query string
		contentType: false, // Let the content type be set by FormData
		data: formData,
		success: function(response) {
			// Handle success (you can update the UI or notify the user)
			alert('User updated successfully.');
			$('#editEmp').modal('hide');
		},
		error: function(xhr, status, error) {
			// Handle error
			console.log("Error updating user: ", xhr.responseText);
			alert('Error updating user: ' + xhr.responseText);
		}
	});
});*/