$("#empSave").on('click', function(e) {
	alert("Button Clicked....");
    e.preventDefault();

    let fName = $("input[name='fName']").val();
    let lName = $("input[name='lName']").val();
    let mobile_no = $("input[name='mobile_no']").val();
    let address = $("input[name='address']").val();
    let designation = $("input[name='designation']").val();
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
        },
        error: function(e) {
            alert("Employee not Added..");
        }
    });
});

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
	console.log("Topic ID is : " + id);

	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/employees/" + id,
		dataType: 'json',
		success: function(data) {
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



$('#editEmpSave').on('click', function() {
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
});