$(document).ready(function() {
    const loggedInUser = JSON.parse(localStorage.getItem('loggedInUser'));
    console.log(loggedInUser);

    if (loggedInUser && loggedInUser.id) {
        showEmployeeLeave(loggedInUser.id);
    } else {
        console.error("No logged-in user found");
    }
});

$("#empLeave").on('click', function(e) {
    alert("Button Clicked....");
    e.preventDefault();

    let startDate = $("#exampleInputFrom1").val();
    let endDate = $("#exampleInputTo1").val();
    let LeaveType = $("select[name='leaveType']").val();
    let subject = $("#exampleInputLeaveSubject1").val();
    let description = $("#exampleInputReason1").val();
    
    const loggedInUser = JSON.parse(localStorage.getItem('loggedInUser'));

    let details = {
        id: loggedInUser.id,         // Get the employee ID from local storage
        startDate: startDate,
        endDate: endDate,
        LeaveType: LeaveType,
        subject: subject,
        description: description,
        status: 'pending',           // Set status to 'pending'
        currentDate: new Date().toISOString().split('T')[0] // Send current date in YYYY-MM-DD format
    };
    
    console.log("Leave Details : ", details);

    $.ajax({
        type: "POST",
        url: '/apply',
        data: $.param(details), // Serialize data into x-www-form-urlencoded format
        success: function(response) {
            alert("Leave Added Successfully.");
            $("#empLeaveForm").trigger("reset");
            showEmployeeLeave(loggedInUser.id);
        },
        error: function(e) {
            alert("Leave not Added.");
        }
    });
});



function showEmployeeLeave(empId) {

   // alert("Table is loading...");
    console.log("Emp id is:", empId);
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: '/leaves/' + empId,
        dataType: 'json',
        success: function(data) {
            console.log("Received data:", data);
            var d = '';

            for (var i = 0; i < data.length; i++) {
                // Calculate total leave days between startDate and endDate
                let startDate = new Date(data[i].startDate);
                let endDate = new Date(data[i].endDate);
                let totalDays = Math.ceil((endDate - startDate) / (1000 * 60 * 60 * 24)) + 1; // Adding 1 to include both start and end dates

				// Displaying status instead of dropdown and applying button-like styling
				let statusDisplay = `
                    <span class="status-button" data-status="${data[i].status}">
                        ${data[i].status}
                    </span>`;
                    
                d += '<tr>' +
                    '<td>' + data[i].currentDate + '</td>' +
                    '<td>' + data[i].subject + '</td>' +
                    '<td>' + data[i].leaveType + '</td>' +  // Fixed case sensitivity
                    '<td>' + totalDays + '</td>' +  // Display calculated total days
                    '<td>' + data[i].description + '</td>' +
 					'<td>' + statusDisplay + '</td>' +
                     '</tr>';
            }

            $('#empLeaveTable').html(d);  // Append the rows to the table
            applyStatusButtonStyles();
            
        },
        error: function() {
            alert("Error loading data...");
        }
    });
}

function applyStatusButtonStyles() {
    $('.status-button').each(function() {
        var status = $(this).data('status'); // Get the status value

        // Common style for all status buttons
        $(this).css({
            'color': 'black',            // Black text color
            'font-weight': 'bold',       // Increase font weight
            'font-size': '16px',         // Increase font size
            'border-radius': '5px',      // Add border radius
            'padding': '5px 10px',       // Add padding
            'border': '1px solid #ccc',  // Border around the status button
            'display': 'inline-block',   // Make it behave like a button
            'cursor': 'pointer',         // Cursor pointer on hover
        });

        // Apply background color based on the status
        if (status === 'Pending') {
            $(this).css('background-color', '#ffc107'); // Yellow for Pending
        } else if (status === 'Approved') {
            $(this).css('background-color', '#28a745'); // Green for Approved
        } else if (status === 'Rejected') {
            $(this).css('background-color', '#dc3545'); // Red for Rejected
        }
    });
}