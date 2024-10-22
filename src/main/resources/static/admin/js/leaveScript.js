$(document).ready(function() {
    alert("Table Loading...");
    showAllEmployeeLeave();
});

function showAllEmployeeLeave() {
    alert("Table is loading...");

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: '/admin/all',
        dataType: 'json',
        success: function(data) {
            console.log("Received data:", data);
            var d = '';

            for (var i = 0; i < data.length; i++) {
                let startDate = new Date(data[i].startDate);
                let endDate = new Date(data[i].endDate);
                let totalDays = Math.ceil((endDate - startDate) / (1000 * 60 * 60 * 24)) + 1; // Adding 1 to include both start and end dates

                // Accessing employee details from the employeeL object
                let employeeId = data[i].employeeL.id;
                let firstName = data[i].employeeL.fName;
                let lastName = data[i].employeeL.lName;

                // Creating dropdown for status change
                let statusDropdown = `
                    <select class="form-control status-select" onchange="updateStatus(this, '${data[i].aId}')">
                        <option value="Pending" ${data[i].status === 'Pending' ? 'selected' : ''}>Pending</option>
                        <option value="Approved" ${data[i].status === 'Approved' ? 'selected' : ''}>Approved</option>
                        <option value="Rejected" ${data[i].status === 'Rejected' ? 'selected' : ''}>Rejected</option>
                    </select>`;

                d += '<tr>' +
                    '<td>' + employeeId + '</td>' +
                    '<td>' + firstName + '</td>' +
                    '<td>' + lastName + '</td>' +
                    '<td>' + data[i].currentDate + '</td>' +
                    '<td>' + data[i].description + '</td>' +
                    '<td>' + totalDays + '</td>' +
                    '<td>' + statusDropdown + '</td>' +
                    '</tr>';
            }

            $('#allEmpLeaves').html(d);  // Append the rows to the table

            // After table is loaded, apply color styling
            applyStatusStyles();
        },
        error: function() {
            alert("Error loading data...");
        }
    });
}

function applyStatusStyles() {
    $('.status-select').each(function() {
        var status = $(this).val(); // Get the selected value

        // Common style for all dropdowns
        $(this).css({
            'color': 'black',            // Black text color for all dropdowns
            'font-weight': 'bold',       // Increase font weight
            'font-size': '16px',         // Increase font size
            'border-radius': '5px',      // Add border radius
            'padding': '5px',            // Add padding
            'border': '1px solid #ccc',  // Border around the dropdown
        });

        // Apply background color based on the selected status
        if (status === 'Pending') {
            $(this).css('background-color', '#ffc107'); // Yellow for Pending
        } else if (status === 'Approved') {
            $(this).css('background-color', '#28a745'); // Green for Approved
        } else if (status === 'Rejected') {
            $(this).css('background-color', '#dc3545'); // Red for Rejected
        }
    });

    // Trigger color change on select change
    $('.status-select').on('change', function() {
        var status = $(this).val();
        if (status === 'Pending') {
            $(this).css('background-color', '#ffc107');
        } else if (status === 'Approved') {
            $(this).css('background-color', '#28a745');
        } else if (status === 'Rejected') {
            $(this).css('background-color', '#dc3545');
        }
    });
}


// Function to handle status update
function updateStatus(selectElement, aId) {
    let newStatus = selectElement.value;
    console.log(`Updating status for ID: ${aId} to ${newStatus}`);

    $.ajax({
        type: "PUT",
        url: '/admin/leave/status/' + aId,  // This endpoint should handle status update in the backend
        contentType: "application/json",
        data: JSON.stringify({
            "aId": aId,
            "status": newStatus
        }),
        success: function(response) {
            alert("Status updated successfully!");
        },
        error: function() {
            alert("Error updating status.");
        }
    });
}
