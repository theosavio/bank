console.log('script is loaded');

// Create Account
function createAccount(event) {
    event.preventDefault();  // Prevent the form from submitting
    console.log('createAccount function called');
    let accountHolderName = document.getElementById("createName").value;
    let balance = document.getElementById("createBalance").value;

    fetch('/api/accounts', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            accountHolderName: accountHolderName,
            balance: parseFloat(balance)
        })
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById("response").innerHTML = "Account created successfully! " + JSON.stringify(data);
    })
    .catch(error => console.error('Error:', error));
}

// Get Account
function getAccount(event) {
    event.preventDefault();  // Prevent the form from submitting
    console.log('getAccount function called');
    let accountId = document.getElementById("getId").value;

    fetch(`/api/accounts/${accountId}`)
    .then(response => response.json())
    .then(data => {
        document.getElementById("response").innerHTML = "Account details: " + JSON.stringify(data);
    })
    .catch(error => console.error('Error:', error));
}

// Deposit Amount
function depositAmount(event) {
    event.preventDefault();  // Prevent the form from submitting
    console.log('depositAmount function called');
    let accountId = document.getElementById("depositId").value;
    let amount = document.getElementById("depositAmount").value;

    fetch(`/api/accounts/${accountId}/deposit`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ amount: parseFloat(amount) })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        document.getElementById("response").innerHTML = "Deposit successful! Updated Account: " + JSON.stringify(data);
    })
    .catch(error => console.error('Error:', error));
}

// Withdraw Amount
function withdrawAmount(event) {
    event.preventDefault();  // Prevent the form from submitting
    console.log('withdrawAmount function called');
    let accountId = document.getElementById("withdrawId").value;
    let amount = document.getElementById("withdrawAmount").value;

    fetch(`/api/accounts/${accountId}/withdraw`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ amount: parseFloat(amount) })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        document.getElementById("response").innerHTML = "Withdrawal successful! Updated Account: " + JSON.stringify(data);
    })
    .catch(error => console.error('Error:', error));
}

// Delete Account
function deleteAccount(event) {
    event.preventDefault();  // Prevent the form from submitting
    console.log('deleteAccount function called');
    let accountId = document.getElementById("deleteId").value;

    fetch(`/api/accounts/${accountId}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.ok) {
            document.getElementById("response").innerHTML = "Account deleted successfully!";
        } else {
            document.getElementById("response").innerHTML = "Failed to delete account.";
        }
    })
    .catch(error => console.error('Error:', error));
}

// List All Accounts
function listAllAccounts(event) {
    event.preventDefault();  // Prevent the form from submitting
    console.log('listAllAccounts function called');
    fetch('/api/accounts/getall')
    .then(response => response.json())
    .then(data => {
        document.getElementById("response").innerHTML = "All Accounts: " + JSON.stringify(data);
    })
    .catch(error => console.error('Error:', error));
}
