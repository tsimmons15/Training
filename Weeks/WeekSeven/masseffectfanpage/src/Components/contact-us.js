export default function ContactUs(props) {
    return (
        <>
            <p>{props.email} &dot; {props.phone} &dot; {props.addr}</p>
        </>
    );
}