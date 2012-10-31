For the latest information, see: https://github.com/greese/dasein-cloud/wiki

For discussion, join the Dasein Cloud Google Groups group: https://groups.google.com/forum/#!forum/dasein-cloud

Dasein Cloud (pronounced "da z-eye-n") is a Java-based cloud abstraction layer that enables 
programmers to build applications under a "write-once, run against any cloud" philosophy. It provides an
abstract model under which most IaaS and some PaaS services can be modeled. You write
your application to the Dasein Cloud model and it is then translated into the underlying
cloud provider model.

It's not a least-common denominator approach, however. Dasein Cloud serves up a meta-data
layer that enables an application to dynamically discover the capabilities of the cloud with
which it is operating. You therefore create conditional logic based solely on the Dasein 
model and successfully deal with the nuances and unique capabilities of each cloud.

"Dasein" comes from the philosophical concept called Dasein (http://en.wikipedia.org/wiki/Dasein).

The main Dasein Cloud project is a shell that contains many different Git sub-modules.
The most important sub-module is dasein-cloud-core. The core contains the Dasein Cloud
object model against which you write your applications. You should not have any compile-time
dependencies on any other Dasein sub-modules. All other sub-modules except dasein-cloud-test,
dasein-cloud-mock, and dasein-cloud-cli are implementations of the Dasein Cloud model
for specific clouds. You include the ones you need as runtime dependencies.

dasein-cloud-core also includes examples of using Dasein Cloud in an application.

dasein-cloud-test is a pre-configured test suite for people implementing Dasein Cloud
for specific clouds so they can verify their implementation functions according to
the specification.

dasein-cloud-mock is an implementation of Dasein Cloud that mocks an actual cloud. When
testing your cloud applications, you run the tests against dasein-cloud-mock instead of
a specific cloud in order to facilitate unit tests. It mocks delays in state changes (such
as the fact that launching a VM is never immediate).

dasein-cloud-cli is a command-line library for talking to clouds.
