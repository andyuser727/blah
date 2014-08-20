<html>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="/static/ready.js"></script>
<script type="text/javascript" >
    $(document).ready(function(){
        readyFuncs();
    });


</script>

<body>

<div id="holder"/>
<button id="testButton" name="testButton">Click Me!</button>

<form id="Person">
    <label>Name:</label>
    <input type="text" name="name" id="name"/>
    <label>Email</label>
    <input type="text" name="email" id="email"/>
    <label>Password</label>
    <input type="password" name="password"/>

    <input type="text" name="testF" id="testF"/>
</form>

</body>
</html>