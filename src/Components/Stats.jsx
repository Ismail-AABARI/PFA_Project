import CountUp from 'react-countup';
import react1 from "../assets/stars.gif";


export const stats = [
    {
      id: "stats-1",
      title: "User Active",
      value: "3800+",
    },
    {
      id: "stats-2",
      title: "Trusted by Company",
      value: "230+",
    },
    {
      id: "stats-3",
      title: "Transaction",
      value: "230+",
    },
  ];


  const Stats = () => (
    <section className="flex justify-center items-center flex-row flex-wrap bg-black  mt-6 " style={{ backgroundImage: `url(${react1})` }}>
      {stats.map((stat) => (
        <div key={stat.id} className="flex-1 flex justify-start items-center flex-row m-3 ml-40">
          <CountUp end={parseFloat(stat.value)} className='text-white font-poppins font-semibold xs:text-[40px] text-[30px] xs:leading-[53px] leading-[43px] ' />
          <p className="font-poppins font-normal xs:text-[20.45px] text-[15.45px] xs:leading-[26.58px] leading-[21.58px] text-gradient uppercase ml-3">
            {stat.title}
          </p>
        </div>
      ))}
    </section>
  );
  
  export default Stats;
  